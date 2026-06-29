package com.cccstudio.chess_viking_variants.api.pgn;

import com.cccstudio.chess_viking_variants.api.Languages;
import com.cccstudio.chess_viking_variants.api.Move;
import com.cccstudio.chess_viking_variants.api.PlayContext;

public class NativeParser implements PGNParser{

    @Override
    public PlayContext parsePGN(String file, Languages lang) throws PGNParseException {
        return null;
    }

    @Override
    public String parseMove(Move move, Languages lang) {
        return "";
    }

    @Override
    public Move parseNotation(String notation, Languages lang) throws PGNParseException {
        return null;
    }

}
