package com.cccstudio.chess_viking_variants.api.vanilla;

import com.cccstudio.chess_viking_variants.api.Board;
import com.cccstudio.chess_viking_variants.api.CasePos;
import com.cccstudio.chess_viking_variants.api.PieceType;

import java.util.LinkedHashSet;
import java.util.Map;

public class ClassicBoard extends Board {

    @Override
    public Map<PieceType, Byte[]> buildBoard() {
        return Map.of();
    }

    @Override
    public LinkedHashSet<CasePos> setup() {
        LinkedHashSet<CasePos> casePosSet = new LinkedHashSet<>();
        for(int x = 0; x < 8; x++){
            for(int y = 0; y < 8; y++){
                casePosSet.add(new CasePos(x, y));
            }
        }
        return casePosSet;
    }

    @Override
    public int height() {
        return 8;
    }

}
