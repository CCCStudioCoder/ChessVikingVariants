package com.cccstudio.chess_viking_variants.api;

import com.cccstudio.chess_viking_variants.api.pgn.Notation;

import java.util.Map;

/**
 * A {@link Move} is something that happens during a player's turn, when they finish moving a piece
 */
public interface Move {

    /**
     * What does the move do.
     * @param board
     * The board on which the move is played.
     */
    Map<PieceType, Byte[]> apply(Board board);

    CasePos source();
    CasePos destination();

    Notation[] getNotation(Board board);

    /**
     * Determine who's turn it is after the precedent move has been played.
     * @return
     * The index of the player who's having his turn now.
     */
    default int passTurn() {
        return PlayContext.get().nextTurn();
    }

}