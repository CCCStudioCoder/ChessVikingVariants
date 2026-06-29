package com.cccstudio.chess_viking_variants.api.pgn;

import com.cccstudio.chess_viking_variants.api.Languages;
import com.cccstudio.chess_viking_variants.api.Move;
import com.cccstudio.chess_viking_variants.api.PlayContext;

/**
 * This class is used the convert {@code .pgn4} files into games and vice versa.
 */
public interface PGNParser {

    PlayContext parsePGN(String file, Languages lang) throws PGNParseException;

    String parseMove(Move move, Languages lang);

    Move parseNotation(String notation, Languages lang) throws PGNParseException;

}