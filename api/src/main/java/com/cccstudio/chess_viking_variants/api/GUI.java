package com.cccstudio.chess_viking_variants.api;

public abstract class GUI {

    public abstract String render();

    public abstract Placement getDimension();

    public void close() {
        //TODO
    }

    public record Placement(int x, int y, int width, int height) {}

}