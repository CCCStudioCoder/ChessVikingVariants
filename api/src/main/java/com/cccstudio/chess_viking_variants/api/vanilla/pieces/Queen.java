package com.cccstudio.chess_viking_variants.api.vanilla.pieces;

import com.cccstudio.chess_viking_variants.api.*;
import com.cccstudio.chess_viking_variants.api.vanilla.ContinuousDirMask;
import com.cccstudio.chess_viking_variants.api.vanilla.SimpleMove;

import java.net.URL;
import java.util.Objects;
import java.util.Set;

public class Queen implements PieceType {

    public static final ContinuousDirMask MASK = ContinuousDirMask.of(King.MASK);

    @Override
    public Set<Move> getPseudoMoves(CasePos from, byte player, Board board) {
        return SimpleMove.around(from, MASK.applyAt(from, player, board));
    }

    @Override
    public URL getImagePath() {
        return Objects.requireNonNull(getClass().getResource("/pieces/queen.png"));
    }

    @Override
    public String getSymbol(Languages lang) {
        return switch (lang) {
            case ENGLISH -> "Q";
            case FRENCH, SPANISH -> "D";
        };
    }

}
