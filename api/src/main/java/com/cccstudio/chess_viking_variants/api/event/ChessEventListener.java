package com.cccstudio.chess_viking_variants.api.event;

import java.util.function.Consumer;

public record ChessEventListener<T extends Event>(Class<T> event, Consumer<T> callback) {
}