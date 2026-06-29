package com.cccstudio.chess_viking_variants.api;

public interface Variant {

    Board createGame(PlayContext ctx);

    String getName();

}