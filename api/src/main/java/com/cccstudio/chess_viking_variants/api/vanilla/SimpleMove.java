package com.cccstudio.chess_viking_variants.api.vanilla;

import com.cccstudio.chess_viking_variants.api.*;
import com.cccstudio.chess_viking_variants.api.pgn.Notation;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class SimpleMove implements Move {

    CasePos from;
    CasePos to;

    /**
     * Create an auto move instance.
     * @param from
     * The case from which the move starts.
     * @param to
     * The case where the move ends.
     */
    public SimpleMove(CasePos from, CasePos to) {
        this.from = from;
        this.to = to;
    }

    /**
     * Transform all the destination cases to moves.
     * @param from
     * The case from which all the returned moves will start.
     * @param destinations
     * The different available destination from the {@code from} case.
     * @return A set of move created with {@code new SimpleMove()}.
     * @see DirMask
     */
    public static Set<Move> around(CasePos from, Set<CasePos> destinations) {
        return destinations.stream().map(to -> new SimpleMove(from, to)).collect(Collectors.toSet());
    }

    @Override
    public Map<PieceType, Byte[]> apply(Board board) {
        board.setPieceAt(from.x(), from.y(), PieceInstance.EMPTY, false);
        return board.bitboards;
    }

    @Override
    public CasePos source() {
        return this.from;
    }

    @Override
    public CasePos destination() {
        return this.to;
    }

    @Override
    public Notation[] getNotation(Board board) {
        return Notation.of(board, from, to);
    }

}