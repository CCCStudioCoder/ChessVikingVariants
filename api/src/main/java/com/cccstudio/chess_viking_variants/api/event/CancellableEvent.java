package com.cccstudio.chess_viking_variants.api.event;

public abstract class CancellableEvent extends Event {

    public boolean isCancelled;

    public CancellableEvent() {
        this.isCancelled = false;
    }

    public void cancel() {
        this.isCancelled = true;
    }

}