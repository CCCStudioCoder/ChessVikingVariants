package com.cccstudio.chess_viking_variants.app.communication;

public class BoardDTO {

    public record Move(int from, int to, String promotion) {}

}