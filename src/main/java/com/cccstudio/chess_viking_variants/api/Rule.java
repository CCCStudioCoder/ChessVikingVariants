package com.cccstudio.chess_viking_variants.api;

public interface Rule {

    boolean apply();

    default Type getType() {
        return Type.AFTER_MOVE_COMPLETED;
    }

    enum Type {
        AFTER_PIECE_CAPTURED,
        BEFORE_PIECE_CAPTURED,
        AFTER_MOVE_COMPLETED,
        BEFORE_MOVE_COMPLETED,
        ON_SOMEONE_S_TURN_START,
        ON_GLOBAL_TURN_START
    }

    /**
     * @return
     * If at least one of the listeners cancelled the action.
     */
    static boolean applyListenersOf(Type ignored) {
        return true; //TODO
    }

}