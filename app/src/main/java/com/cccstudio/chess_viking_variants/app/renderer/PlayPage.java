package com.cccstudio.chess_viking_variants.app.renderer;

import com.cccstudio.chess_viking_variants.api.PlayContext;
import com.cccstudio.chess_viking_variants.api.Variant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PlayPage {

    @GetMapping("/play")
    public String play(Model model) {
        model.addAttribute("variant", PlayContext.get().findData("variant", Variant.class).getName());
        return "play";
    }

}