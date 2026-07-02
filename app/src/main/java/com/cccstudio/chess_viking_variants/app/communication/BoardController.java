package com.cccstudio.chess_viking_variants.app.communication;

import com.cccstudio.chess_viking_variants.api.Board;
import com.cccstudio.chess_viking_variants.app.renderer.BoardRenderer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

    BoardRenderer renderer = new BoardRenderer();

    @GetMapping("/api/board")
    @ResponseBody
    public String board(Board board){
        return renderer.render(board);
    }

}