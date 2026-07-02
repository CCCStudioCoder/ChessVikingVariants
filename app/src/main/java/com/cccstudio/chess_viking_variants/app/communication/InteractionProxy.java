package com.cccstudio.chess_viking_variants.app.communication;

import com.cccstudio.chess_viking_variants.api.PlayContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class InteractionProxy {

    @PostMapping("/api/available_moves")
    @ResponseBody
    public Set<BoardDTO.Move> availableMoves() {
        return PlayContext.getBoard()
                .getAvailableMoves(PlayContext.get().findData("turn", Byte.class))
                .stream().map(move -> new BoardDTO.Move(
                        move.source().toIndex(), move.destination().toIndex(), null)
                ).collect(Collectors.toSet());
    }

    @GetMapping("/api/is_legal")
    @ResponseBody
    public boolean isLegal(@RequestParam int from, @RequestParam int to) {
        return true;
    }

}