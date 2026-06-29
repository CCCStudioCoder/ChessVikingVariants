package com.cccstudio.chess_viking_variants.api.pgn;

import com.cccstudio.chess_viking_variants.api.*;
import com.cccstudio.chess_viking_variants.api.vanilla.pieces.Pawn;

import java.util.Set;

public interface Notation {

    String parse(Languages lang);

    float position();

    static Notation[] of(Board board, CasePos from, CasePos to) {
        return new Notation[]{
                piece(board.getPieceAt(to).pieceType, from),
                precision(board, from, to),
                capture(board, to),
                target(to),
                check(board, to)
        };
    }

    static Notation impl(String notation, float pos) {
        return new Notation() {
            @Override
            public String parse(Languages lang) {
                return notation;
            }
            @Override
            public float position() {
                return pos;
            }
        };
    }

    static Notation piece(PieceType pieceType, CasePos pos) {
        return new Notation() {
            @Override
            public String parse(Languages lang) {
                if(pieceType instanceof Pawn) {
                    return pos.field();
                }
                return pieceType.getSymbol(lang);
            }
            @Override
            public float position() { return 0; }
        };
    }

    static Notation precision(Board board, CasePos from, CasePos to) {
        PieceInstance movingPiece = board.getPieceAt(from);
        Set<Move> available = movingPiece.pieceType.getPseudoMoves(to, movingPiece.owner, board);

        StringBuilder result = new StringBuilder();
        int state = 0;

        for(Move move : available) {
            CasePos destination = move.destination();

            if(board.getPieceAt(destination).equals(movingPiece)) {
                if(destination.x() == from.x() && state != 1) {
                    result.append(from.field());
                    if(state == 2) break;
                    state = 1;
                } else if(destination.y() == from.y() && state != 2) {
                    result.append(from.row());
                    if(state == 1) break;
                    state = 2;
                }
            }
        }

        return impl(result.toString(), 1);
    }

    static Notation capture(Board board, CasePos target) {
        return impl(board.getPieceAt(target).isEmpty() ? "" : "x", 2);
    }

    static Notation target(CasePos pos) {
        return impl(pos.notation(), 3);
    }

    static Notation check(Board board, CasePos target) {
        return impl("", 4); //TODO
    }

}