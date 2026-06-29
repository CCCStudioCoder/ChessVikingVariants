package com.cccstudio.chess_viking_variants.api.event;

import java.util.HashSet;
import java.util.Set;

public class EventRegistry {

    private static EventRegistry instance;

    private final Set<ChessEventListener<Event>> listeners = new HashSet<>();

    private boolean canSend = true;

    public EventRegistry() {
        instance = this;
    }

    public void addListener(ChessEventListener<Event> listener) {
        listeners.add(listener);
    }

    public void removeListener(ChessEventListener<Event> listener) {
        listeners.remove(listener);
    }

    public void setSendingMode(boolean doesSend) {
        canSend = doesSend;
    }

    public <T extends Event> void fire(T event) {
        if(!canSend) return;

        for (ChessEventListener<Event> listener : listeners) {
            if(listener.event().isInstance(event)) {
                listener.callback().accept(event);
            }
        }
    }

    /**
     * @return
     * {@code false} if the event has been cancelled, {@code true} otherwise
     */
    public <T extends CancellableEvent> boolean fireCancellable(T event) {
        if(!canSend) return true;

        for (ChessEventListener<Event> listener : listeners) {
            if(listener.event().isInstance(event)) {
                listener.callback().accept(event);
                if(event.isCancelled) return false;
            }
        }
        return true;
    }

    public <S, T extends ComputationEvent<S>> S fireEditable(T event) {
        if(!canSend) return event.computed;

        for (ChessEventListener<Event> listener : listeners) {
            if(listener.event().isInstance(event)) {
                listener.callback().accept(event);
            }
        }
        return event.computed;
    }

    public static EventRegistry get() {
        return instance;
    }

}