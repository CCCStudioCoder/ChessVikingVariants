package com.cccstudio.chess_viking_variants.api.vanilla.pieces;

import com.cccstudio.chess_viking_variants.api.*;
import com.cccstudio.chess_viking_variants.api.vanilla.DirMask;
import com.cccstudio.chess_viking_variants.api.vanilla.SimpleMove;

import java.net.URL;
import java.util.Objects;
import java.util.Set;

public class Knight implements PieceType {

    private static final DirMask MASK = new DirMask(Set.of(
            new CasePos(-2, -1),
            new CasePos(-2, 1),
            new CasePos(-1, -2),
            new CasePos(-1, 2),
            new CasePos(1, -2),
            new CasePos(1, 2),
            new CasePos(2, -1),
            new CasePos(2, 1)
    ));

    @Override
    public Set<Move> getPseudoMoves(CasePos from, byte player, Board board) {
        return SimpleMove.around(from, MASK.applyAt(from, player, board));
    }

    @Override
    public String getSymbol(Languages lang) {
        return switch (lang) {
            case ENGLISH -> "K";
            case SPANISH, FRENCH -> "C";
        };
    }

    @Override
    public String getName() {
        return "Knight";
    }

    @Override
    public Class<? extends PieceType> clazz() {
        return Knight.class;
    }

}