package com.cccstudio.chess_viking_variants.app.html;

import java.util.Map;

public sealed interface HTMLNode permits HTMLContainer, HTMLImage, HTMLText {

    String tag();

    Map<String, String> attributes();

    String content();

    default String render() {
        return "<%s %s>%s</%s>".formatted(
                tag(),
                attributes().entrySet().stream().map(entry ->
                        " %s=\"%s\"".formatted(entry.getKey(), entry.getValue())),
                content(),
                tag()
        );
    }

}