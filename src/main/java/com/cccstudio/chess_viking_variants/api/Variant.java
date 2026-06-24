package com.cccstudio.chess_viking_variants.api;

public interface Variant {

    Game createGame(PlayContext ctx);

    String getName();

}