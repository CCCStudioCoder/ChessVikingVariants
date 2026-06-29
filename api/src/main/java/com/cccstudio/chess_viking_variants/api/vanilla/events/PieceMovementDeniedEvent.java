package com.cccstudio.chess_viking_variants.api.vanilla.events;

import com.cccstudio.chess_viking_variants.api.PieceInstance;
import com.cccstudio.chess_viking_variants.api.event.CancellableEvent;

public class PieceMovementDeniedEvent extends CancellableEvent {

    public static final String CHECK = "check";
    public static final String OTHER_S_PIECE = "other's piece";

    public final PieceInstance piece;
    public final String reason;

    public PieceMovementDeniedEvent(PieceInstance piece, String reason) {
        this.piece = piece;
        this.reason = reason;
    }

}
