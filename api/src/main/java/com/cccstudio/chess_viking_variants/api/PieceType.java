package com.cccstudio.chess_viking_variants.api;

import java.io.InputStream;
import java.util.Objects;
import java.util.Set;

public interface PieceType {

    Set<Move> getPseudoMoves(CasePos from, byte player, Board board);

    default InputStream getImage() {
        return Objects.requireNonNull(clazz().getResourceAsStream("/pieces/" + getName() + ".png"));
    }

    String getSymbol(Languages lang);

    String getName();

    Class<? extends PieceType> clazz();

    PieceType EMPTY = new PieceType() {
        @Override
        public Set<Move> getPseudoMoves(CasePos target, byte player, Board board) {
            return Set.of();
        }
        @Override
        public String getSymbol(Languages lang) {
            return "";
        }
        @Override
        public String getName() {
            return "";
        }
        @Override
        public Class<? extends PieceType> clazz() {
            return PieceType.class;
        }
    };

    /**
     * This is a default implementation of the classic equals function. it's based on the name of the piece.
     * If your {@link PieceType} can have the same name as another, you should implement this method.
     */
    default boolean equals(PieceType other) {
        return this.getName().equals(other.getName());
    }

}