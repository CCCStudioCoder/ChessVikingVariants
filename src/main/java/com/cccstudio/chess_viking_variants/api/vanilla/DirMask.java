package com.cccstudio.chess_viking_variants.api.vanilla;

import com.cccstudio.chess_viking_variants.api.Board;
import com.cccstudio.chess_viking_variants.api.CasePos;
import com.cccstudio.chess_viking_variants.api.PlayContext;

import java.util.HashSet;
import java.util.Set;

public class DirMask {

    protected final Set<CasePos> dirs;

    public DirMask(Set<CasePos> dirs) {
        this.dirs = dirs;
    }

    /**
     * This function can be overridden to slightly modify the behavior of
     * {@link DirMask#applyAt} without having to completely rewrite it.
     * @param board
     * Shortcut to avoid slow computation on each iteration. Holds the board on which we apply the mask.
     * @param x
     * The x-coordinate of the initial target case.
     * @param y
     * The x-coordinate of the initial target case.
     * @param player
     * The ID of the player for whom we are applying the mask. If a non-empty case is found, it's up to him whether to
     * consider it as a capture or an illegal move.
     * @param dir
     * A {@link CasePos} used a direction.
     * @return
     * The list of all the cases that match with the mask at the given coordinates.
     */
    protected Set<CasePos> performAt(Board board, int x, int y, int player, CasePos dir) {
        if(board.containsPieceAt(x, y)) {
            if(board.getPieceAt(x, y).owner != player) return Set.of(new CasePos(x, y));
        }
        return Set.of();
    }

    /**
     * Apply the mask at the given coordinates.
     * @return
     * All the cases that the mask covers from the given coordinate.
     */
    public Set<CasePos> applyAt(CasePos coords, int player) {
        return this.applyAt(coords.x(), coords.y(), player);
    }

    /**
     * Apply the mask at the given coordinates.
     * @return
     * All the cases that the mask covers from the given coordinate.
     */
    public Set<CasePos> applyAt(int x, int y, int player) {
        Set<CasePos> result = new HashSet<>();
        Board board = PlayContext.getBoard();
        for(CasePos dir : dirs){
            int newX = x + dir.x();
            int newY = y + dir.y();
            Set<CasePos> targets = performAt(board, newX, newY, player, dir);
            result.addAll(targets);
        }
        return result;
    }

}