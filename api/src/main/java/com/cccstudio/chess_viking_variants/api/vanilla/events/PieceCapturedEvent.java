package com.cccstudio.chess_viking_variants.api.vanilla.events;

import com.cccstudio.chess_viking_variants.api.CasePos;
import com.cccstudio.chess_viking_variants.api.event.CancellableEvent;

public class PieceCapturedEvent extends CancellableEvent {

    public final byte owner;
    public final CasePos pos;

    public PieceCapturedEvent(byte owner, CasePos pos) {
        this.owner = owner;
        this.pos = pos;
    }

}