package com.cccstudio.chess_viking_variants.app.communication;

import com.cccstudio.chess_viking_variants.api.PlayContext;
import org.springframework.stereotype.Controller;

import java.util.Set;

@Controller
public class InteractionProxy {

    /*public Set<BoardDTO.Move> availableMoves() {
        return PlayContext.getBoard()
                .getAvailableMoves(PlayContext.get().findData("turn", Byte.class))
                .stream().map(move -> new BoardDTO.Move(move.source(), move.destination(), null));
    }*/

}