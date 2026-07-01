package com.cccstudio.chess_viking_variants.api.vanilla.pieces;

import com.cccstudio.chess_viking_variants.api.*;
import com.cccstudio.chess_viking_variants.api.vanilla.DirMask;
import com.cccstudio.chess_viking_variants.api.vanilla.SimpleMove;

import java.net.URL;
import java.util.Objects;
import java.util.Set;

public class King implements PieceType {

    public static final DirMask MASK = new DirMask(Set.of(
            new CasePos(-1, 0),
            new CasePos(1, 0),
            new CasePos(0, -1),
            new CasePos(0, 1),
            new CasePos(-1, -1),
            new CasePos(-1, 1),
            new CasePos(1, -1),
            new CasePos(1, 1)
    ));

    @Override
    public Set<Move> getPseudoMoves(CasePos from, byte player, Board board) {
        return SimpleMove.around(from, MASK.applyAt(from, player, board));
    }

    @Override
    public String getSymbol(Languages lang) {
        return switch (lang) {
            case ENGLISH -> "K";
            case FRENCH, SPANISH -> "R";
        };
    }

    @Override
    public String getName() {
        return "King";
    }

    @Override
    public Class<? extends PieceType> clazz() {
        return King.class;
    }

}
