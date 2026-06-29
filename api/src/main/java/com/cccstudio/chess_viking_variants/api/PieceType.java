package com.cccstudio.chess_viking_variants.api;

import java.net.URL;
import java.util.Objects;
import java.util.Set;

public interface PieceType {

    Set<Move> getPseudoMoves(CasePos from, byte player, Board board);

    URL getImagePath();

    String getSymbol(Languages lang);

    PieceType EMPTY = new PieceType() {
        @Override
        public Set<Move> getPseudoMoves(CasePos target, byte player, Board board) {
            return Set.of();
        }
        @Override
        public URL getImagePath() {
            return Objects.requireNonNull(getClass().getResource("/empty.png"));
        }
        @Override
        public String getSymbol(Languages lang) {
            return "";
        }
    };

    /**
     * This is a default implementation of the classic equals function. it's based on the image path of the piece.
     * If your {@link PieceType} can have the same image as another, you should implement this method.
     */
    default boolean equals(PieceType other) {
        return this.getImagePath().getPath().equals(other.getImagePath().getPath());
    }

}