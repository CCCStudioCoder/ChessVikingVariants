package com.cccstudio.chess_viking_variants.api.vanilla.pieces;

import com.cccstudio.chess_viking_variants.api.*;

import java.net.URL;
import java.util.Objects;
import java.util.Set;

public class Pawn implements PieceType {

    @Override
    public Set<Move> getPseudoMoves(CasePos from, byte player, Board board) {
        return Set.of(); //TODO
    }

    @Override
    public URL getImagePath() {
        return Objects.requireNonNull(getClass().getResource("/pieces/pawn.png"));
    }

    @Override
    public String getSymbol(Languages lang) {
        return "";
    }

}
