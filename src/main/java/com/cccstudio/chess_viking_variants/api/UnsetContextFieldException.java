package com.cccstudio.chess_viking_variants.api;

public class UnsetContextFieldException extends RuntimeException {

    public UnsetContextFieldException(String name) {
        super("Property \"" + name + "\" is not set in this context.");
    }

}
