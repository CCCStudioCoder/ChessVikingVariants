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
    public String getSymbol(Languages lang) {
        return "";
    }

    @Override
    public String getName() {
        return "Paw";
    }

    @Override
    public Class<? extends PieceType> clazz() {
        return Pawn.class;
    }

}
