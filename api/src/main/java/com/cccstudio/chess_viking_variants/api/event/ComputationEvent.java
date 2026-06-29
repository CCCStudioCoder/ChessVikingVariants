package com.cccstudio.chess_viking_variants.api.event;

public class ComputationEvent<T> extends Event {

    public T computed;

    public ComputationEvent(T computed) {
        this.computed = computed;
    }

}