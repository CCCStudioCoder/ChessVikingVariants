package com.cccstudio.chess_viking_variants.api;

import com.cccstudio.chess_viking_variants.api.event.*;
import com.cccstudio.chess_viking_variants.api.vanilla.events.*;
import com.cccstudio.chess_viking_variants.api.vanilla.pieces.King;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Board implements Cloneable {

    public Map<PieceType, Byte[]> bitboards;

    protected final LinkedHashSet<CasePos> setup;

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

    public abstract int size();

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
        for(Map.Entry<? extends PieceType, Byte[]> entry : this.bitboards.entrySet()) {
            if(entry.getValue()[i] != 0) {
                return new PieceInstance(entry.getKey(), entry.getValue()[i]);
            }
        }
        return PieceInstance.EMPTY;
    }

    private boolean checkCaptures(Byte[] bitboard, int x, int y, int i) {
        if(bitboard[i] != 0) {
            return EventRegistry.get().fireCancellable(
                    new PieceCapturedEvent(bitboard[i], new CasePos(x, y))
            );
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
        if(!EventRegistry.get().fireCancellable(new MoveCompletedEvent(move))) return;

        this.bitboards = move.apply(this);

        int newTurn = move.passTurn();

        PlayContext.get().registerOrSetField(
                "turn",
                Integer.class,
                newTurn
        );
        if(newTurn == 0) EventRegistry.get().fire(new GlobalTurnStartedEvent());
        EventRegistry.get().fire(new SomeoneSTurnStartedEvent());
    }

    public CasePos kingPos(byte player) {
        return new CasePos(Arrays.asList(Objects.requireNonNull(bitboards.get(new King())))
                .indexOf(player));
    }

    public Set<CasePos> potentiallyPinnedPieces(byte player) {
        Set<CasePos> potentiallyPinned = new LinkedHashSet<>();
        CasePos kingPos = this.kingPos(player);

        for(PieceType type : this.bitboards.keySet()) {
            Set<Move> lines = type.getPseudoMoves(kingPos, (byte) (player == 1 ? 2 : 1), this);
            potentiallyPinned.addAll(lines.stream().map(Move::destination)
                    .filter(destination ->
                            this.getPieceAt(destination).owner == player)
                    .collect(Collectors.toSet()));
        }

        return potentiallyPinned;
    }

    public boolean verifyChecks(byte player, Move move) {
        Board ifPlayed = this.clone();
        EventRegistry.get().setSendingMode(false);
        ifPlayed.applyMove(move);
        EventRegistry.get().setSendingMode(true);

        return ifPlayed.verifyChecks(player);
    }

    public boolean verifyChecks(byte player) {
        CasePos kingPos = this.kingPos(player);

        for(PieceType type : this.bitboards.keySet()) {
            Set<Move> lines = type.getPseudoMoves(kingPos, player, this);
            if(lines.stream().anyMatch(line -> {
                        PieceInstance piece = this.getPieceAt(line.destination());
                        return piece.owner != player && piece.pieceType.equals(type);
                    }
                    )) {
                return false;
            }
        }

        return true;
    }

    public Set<Move> getAvailableMoves(byte player) {
        Set<Move> moves = new LinkedHashSet<>();

        boolean isItCheck = this.verifyChecks(player);
        Set<CasePos> potentiallyPinnedPieces = this.potentiallyPinnedPieces(player);

        for(Map.Entry<PieceType, Byte[]> entry : this.bitboards.entrySet()) {
            PieceType type = entry.getKey();
            Byte[] bitboard = entry.getValue();

            for(int i = 0; i < bitboard.length; i++) {
                byte owner = bitboard[i];
                CasePos pos = new CasePos(i);
                PieceInstance piece = new PieceInstance(type, owner);

                if(owner == 0) continue;
                if(owner != player &&
                    !EventRegistry.get().fireCancellable(new PieceMovementDeniedEvent(
                            piece, PieceMovementDeniedEvent.OTHER_S_PIECE
                    ))) continue;

                for(Move move : type.getPseudoMoves(pos, player, this)) {
                    if(isItCheck || potentiallyPinnedPieces.contains(pos)) {
                        if(!verifyChecks(player, move) &&
                                EventRegistry.get().fireCancellable(new MoveRejectedBecauseOfCheckEvent(move))) {
                            continue;
                        }
                    }

                    moves.add(move);
                }
            }
        }
        return EventRegistry.get().fireEditable(new ComputeLegalMovesEvent(moves));
    }

    @Override
    public abstract Board clone();

}