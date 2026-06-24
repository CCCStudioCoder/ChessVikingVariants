package com.cccstudio.chess_viking_variants.api.vanilla;

import com.cccstudio.chess_viking_variants.api.Languages;
import com.cccstudio.chess_viking_variants.api.Board;
import com.cccstudio.chess_viking_variants.api.CasePos;
import com.cccstudio.chess_viking_variants.api.Move;
import com.cccstudio.chess_viking_variants.api.PieceType;

import java.net.URL;
import java.util.Objects;
import java.util.Set;

public class Knight implements PieceType {

    private static final DirMask MASK = new DirMask(Set.of(
            new CasePos(-2, -1),
            new CasePos(-2, 1),
            new CasePos(-1, -2),
            new CasePos(-1, 2),
            new CasePos(1, -2),
            new CasePos(1, 2),
            new CasePos(2, -1),
            new CasePos(2, 1)
    ));

    @Override
    public Set<Move> getLegalMoves(CasePos from, int player, Board board) {
        return SimpleMove.around(from, MASK.applyAt(from, player));
    }

    @Override
    public URL getImagePath() {
        return Objects.requireNonNull(getClass().getResource("/pieces/knight.png"));
    }

    @Override
    public String getSymbol(Languages lang) {
        return switch (lang) {
            case ENGLISH -> "K";
            case SPANISH, FRENCH -> "C";
        };
    }

}