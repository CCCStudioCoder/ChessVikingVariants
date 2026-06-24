package com.cccstudio.chess_viking_variants.api;

/**
 * An {@link CaseOutOfBoardException} is thrown every time something tries to access a case in board, but it doesn't exist.
 * @see Board
 * @see Board#setup()
 */
public class CaseOutOfBoardException extends RuntimeException {

    public CaseOutOfBoardException(int x, int y) {
        super(String.format("The case at (%d, %d) doesn't exist in the used board.", x, y));
    }

}
