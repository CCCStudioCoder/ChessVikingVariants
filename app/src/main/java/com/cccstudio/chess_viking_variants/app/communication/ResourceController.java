package com.cccstudio.chess_viking_variants.app.communication;

import com.cccstudio.chess_viking_variants.api.PieceType;
import com.cccstudio.chess_viking_variants.api.PlayContext;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/resource")
public class ResourceController {

    private final Map<String, ResponseEntity<@NotNull Resource>> cachedResponses = new HashMap<>();

    /*@GetMapping("/${piece}")
    public ResponseEntity<@NotNull Resource> getPiece(@PathVariable String piece) {
        if(cachedResponses.containsKey(piece)) return cachedResponses.get(piece);

        PieceType pieceType = PlayContext.getBoard().bitboards.keySet().stream()
                    .filter(type -> type.getName().equals(piece)).findFirst().orElseThrow();

        ResponseEntity<@NotNull Resource> result = ResponseEntity.ok()
                .contentType(MediaType.IMAGE_PNG)
                .body(new UrlResource(pieceType.getImagePath()));

        cachedResponses.put(piece, result);
        return result;
    }*/

}
