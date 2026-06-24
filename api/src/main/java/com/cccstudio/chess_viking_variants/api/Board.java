package com.cccstudio.chess_viking_variants.api;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

public abstract class Board {

    public Map<PieceType, Byte[]> bitboards;

    private final LinkedHashSet<CasePos> setup;

    public Board() {
        this.bitboards = buildBoard();
        this.setup = setup();
    }

    public abstract Map<PieceType, Byte[]> buildBoard();

    /**
     * Determine for each case where it needs to be placed.
     * Every time something tries to access a case which is not specified in this set, a {@link CaseOutOfBoardException}
     * should be thrown.
     * @return
     * The coordinates of each cases in the board.
     */
    public abstract LinkedHashSet<CasePos> setup();

    public abstract int height();

    public boolean containsPieceAt(int x, int y) {
        return setup.contains(new CasePos(x, y));
    }

    private int indexOf(int x, int y) {
        CasePos c = new CasePos(x, y);
        Iterator<CasePos> it = setup.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().equals(c)) return i;
            i++;
        }
        throw new CaseOutOfBoardException(x, y);
    }

    public PieceInstance getPieceAt(CasePos pos) {
        return this.getPieceAt(pos.x(), pos.y());
    }

    public PieceInstance getPieceAt(int x, int y) throws CaseOutOfBoardException {
        int i = indexOf(x, y);
        for(Map.Entry<PieceType, Byte[]> entry : this.bitboards.entrySet()) {
            if(entry.getValue()[i] != 0) {
                return new PieceInstance(entry.getKey(), entry.getValue()[i]);
            }
        }
        return PieceInstance.EMPTY;
    }

    private boolean checkCaptures(Byte[] bitboard, int x, int y, int i) {
        if(bitboard[i] != 0) {
            PlayContext.get().registerOrSetField(
                    "last-piece-captured-owner",
                    Byte.class,
                    bitboard[i]
            );
            PlayContext.get().registerOrSetField(
                    "last-piece-captured-position",
                    CasePos.class,
                    new CasePos(x, y)
            );
            if(Rule.applyListenersOf(Rule.Type.BEFORE_PIECE_CAPTURED)) return true;
            Rule.applyListenersOf(Rule.Type.AFTER_PIECE_CAPTURED);
        }
        return false;
    }

    /**
     * Modifies the content of the given case.
     * @param x
     * The x-coordinate of the target case.
     * @param y
     * The y-coordinate of the target case.
     * @param piece
     * Which piece should be placed at the given case.
     * @param checkCap
     * If set as {@code true}, the piece at the target case will, if present, be considered as captured.
     * @throws CaseOutOfBoardException
     * If the given case is not present in this board.
     */
    public void setPieceAt(int x, int y, PieceInstance piece, boolean checkCap) throws CaseOutOfBoardException {
        int index = indexOf(x, y);
        Byte[] bitboard = this.bitboards.get(piece.pieceType);
        for (int i = 0; i < bitboard.length; i++) {
            if (i == index) {
                if (checkCap && checkCaptures(bitboard, x, y, i)) break;

                bitboard[i] = piece.owner;
                bitboards.replace(piece.pieceType, bitboard);
                break;
            }
        }
    }

    public void applyMove(Move move) {
        PlayContext.get().registerOrSetField(
                "move-about-to-complete",
                Move.class,
                move
        );
        if(Rule.applyListenersOf(Rule.Type.BEFORE_MOVE_COMPLETED)) return;

        this.bitboards = move.apply(this);

        Rule.applyListenersOf(Rule.Type.AFTER_MOVE_COMPLETED);

        int newTurn = move.passTurn();

        PlayContext.get().registerOrSetField(
                "turn",
                Integer.class,
                newTurn
        );
        if(newTurn == 0) Rule.applyListenersOf(Rule.Type.ON_GLOBAL_TURN_START);
        Rule.applyListenersOf(Rule.Type.ON_SOMEONE_S_TURN_START);
    }

}