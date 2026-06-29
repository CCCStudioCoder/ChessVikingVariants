package com.cccstudio.chess_viking_variants.api.vanilla.pieces;

import com.cccstudio.chess_viking_variants.api.*;
import com.cccstudio.chess_viking_variants.api.vanilla.ContinuousDirMask;
import com.cccstudio.chess_viking_variants.api.vanilla.SimpleMove;

import java.net.URL;
import java.util.Objects;
import java.util.Set;

public class Bishop implements PieceType {

    public static final ContinuousDirMask MASK = new ContinuousDirMask(Set.of(
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
    public URL getImagePath() {
        return Objects.requireNonNull(getClass().getResource("pieces/bishop.png"));
    }

    @Override
    public String getSymbol(Languages lang) {
        return switch (lang) {
            case ENGLISH -> "B";
            case SPANISH -> "A";
            case FRENCH -> "F";
        };
    }

}
