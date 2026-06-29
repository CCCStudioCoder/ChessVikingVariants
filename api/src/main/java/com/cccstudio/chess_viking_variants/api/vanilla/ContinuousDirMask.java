package com.cccstudio.chess_viking_variants.api.vanilla;

import com.cccstudio.chess_viking_variants.api.Board;
import com.cccstudio.chess_viking_variants.api.CasePos;
import com.cccstudio.chess_viking_variants.api.PieceInstance;

import java.util.HashSet;
import java.util.Set;

public class ContinuousDirMask extends DirMask {

    public ContinuousDirMask(Set<CasePos> dirs) {
        super(dirs);
    }

    public static ContinuousDirMask of(DirMask dirMask) {
        return new ContinuousDirMask(dirMask.dirs);
    }

    @Override
    protected Set<CasePos> performAt(Board board, int x, int y, byte player, CasePos dir) {
        Set<CasePos> result = new HashSet<>();
        while (board.containsPieceAt(x, y)) {
            PieceInstance piece = board.getPieceAt(x, y);
            if (piece.owner != player && !piece.isEmpty()) {
                result.add(new CasePos(x, y));
                break;
            }
            x += dir.x();
            x += dir.y();
        }
        return result;
    }

}
