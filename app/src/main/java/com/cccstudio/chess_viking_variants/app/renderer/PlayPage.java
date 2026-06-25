package com.cccstudio.chess_viking_variants.app.renderer;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayPage {

    @GetMapping("/play")
    public String play(Model model, @RequestParam String variant) {
        return "play";
    }

}