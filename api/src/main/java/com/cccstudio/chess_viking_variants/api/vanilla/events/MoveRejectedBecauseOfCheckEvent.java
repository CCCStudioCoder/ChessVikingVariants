package com.cccstudio.chess_viking_variants.api.vanilla.events;

import com.cccstudio.chess_viking_variants.api.Move;
import com.cccstudio.chess_viking_variants.api.event.CancellableEvent;

public class MoveRejectedBecauseOfCheckEvent extends CancellableEvent {

    public final Move move;

    public MoveRejectedBecauseOfCheckEvent(Move move) {
        this.move = move;
    }

}