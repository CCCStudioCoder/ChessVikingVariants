package com.cccstudio.chess_viking_variants.api;

import com.cccstudio.chess_viking_variants.Languages;

/**
 * This class is used the convert {@code .pgn4} files into games and vice versa.
 */
public interface PGNParser {

    PlayContext parsePGN(String file, Languages lang);

    String parseMove(Move move, Languages lang);

    Move parseNotation(String notation, Languages lang);

}