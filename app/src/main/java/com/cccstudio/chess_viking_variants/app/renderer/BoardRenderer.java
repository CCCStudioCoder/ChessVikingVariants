package com.cccstudio.chess_viking_variants.app.renderer;

import com.cccstudio.chess_viking_variants.api.Board;
import com.cccstudio.chess_viking_variants.api.CasePos;
import com.cccstudio.chess_viking_variants.app.html.HTMLDiv;
import com.cccstudio.chess_viking_variants.app.html.HTMLImage;
import com.cccstudio.chess_viking_variants.app.html.HTMLJSScript;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BoardRenderer {

    public String render(Board board) {
        return """
               %s
               %s
               """.formatted(
                    IntStream.range(0, board.setup().size())
                            .mapToObj(i -> renderCase(board, i).render())
                            .collect(Collectors.joining()), // Should add Board#size
                    createScript(board)
               );
    }

    public String createScript(Board board) {
        return createClickBehavior(board);
    }

    public String createClickBehavior(Board board) {
        return """
               let clicked = -1;
               
               async function caseClicked(index) {
                   if(clicked != -1) {
                        const legal = await fetch(`/api/is_legal?from=${clicked}&to=${index}`)
                            .then(r -> r.json());
                        if(legal) {
                            clicked = -1
                            render();
                        }
                   } else {
                        clicked = index;
                   }
               }
               """;
    }

    public String getClickBehavior(Board board, int index) {
        return "async () => await caseClicked(%s)".formatted(index);
    }

    public HTMLDiv renderCase(Board board, int index) {
        CasePos pos = new CasePos(index);

        HTMLDiv div = new HTMLDiv(Map.of(
                "class", resolveCaseClasses(board, pos),
                "onclick", getClickBehavior(board, index)
        ));
        div.addChild(new HTMLImage(board.getPieceAt(pos).pieceType.getName()));
        return div;
    }

    public String resolveCaseClasses(Board board, CasePos pos) {
        return "case " + ((pos.x() + pos.y()) % 2 == 0 ? "white" : "black");
    }

}