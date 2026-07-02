package com.cccstudio.chess_viking_variants.api;

import com.cccstudio.chess_viking_variants.api.vanilla.DirMask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * A {@link CasePos} setup object is used to send positioning data to the renderer.
 * @see Board#bitboards
 * @see Board#setup()
 * @see DirMask
 */
public record CasePos(int x, int y) {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public CasePos(int pos) {
        this(pos % 8, pos / 8);
    }

    /**
     * @return The field of the positon (X)
     */
    public String field() {
        int tempY = this.y;
        int maxExp = 0, withExp = 26;
        StringBuilder sb = new StringBuilder();

        while(withExp < tempY) {
            withExp *= 26;
            maxExp++;
        }

        for(int i = maxExp; i >= 0; i--) {
            sb.append(ALPHABET.charAt(tempY / 26 ^ i));
            tempY %= 26 ^ i;
        }

        return sb.toString();
    }

    /**
     * @return The row of the positon (Y)
     */
    public String row() {
        return "" + (PlayContext.getBoard().height() - y);
    }

    public String notation() {
        return String.format("%s%s", field(), row());
    }

    public int toIndex() {
        Board board = PlayContext.getBoard();
        List<CasePos> setup = board.setup.stream().toList();
        return IntStream.range(0, board.size())
                .filter(i -> setup.get(i).equals(this))
                .findFirst().orElseThrow(() -> new CaseOutOfBoardException(x, y));
    }

}