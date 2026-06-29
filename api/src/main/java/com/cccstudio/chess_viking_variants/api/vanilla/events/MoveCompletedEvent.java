package com.cccstudio.chess_viking_variants.api.vanilla.events;

import com.cccstudio.chess_viking_variants.api.Move;
import com.cccstudio.chess_viking_variants.api.event.CancellableEvent;

public class MoveCompletedEvent extends CancellableEvent {

    public final Move move;

    public MoveCompletedEvent(Move move) {
        this.move = move;
    }

}