package com.cccstudio.chess_viking_variants.app;

import org.jetbrains.annotations.ApiStatus;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChessVikingVariantsApplication {

    @ApiStatus.Internal
	public static void main(String[] args) {
		SpringApplication.run(ChessVikingVariantsApplication.class, args);
	}

}
