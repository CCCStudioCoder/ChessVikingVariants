package com.cccstudio.chess_viking_variants.api;

public class PieceInstance {

    public static final PieceInstance EMPTY = new PieceInstance(PieceType.EMPTY, (byte) 0) {
        @Override
        public boolean isEmpty() {
            return true;
        }
    };

    public final PieceType pieceType;
    public final byte owner;

    public PieceInstance(PieceType pieceType, byte owner) {
        this.pieceType = pieceType;
        this.owner = owner;
    }

    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof PieceInstance instance) {
            return this.pieceType.equals(instance.pieceType) && this.owner == instance.owner;
        }
        return false;
    }
}