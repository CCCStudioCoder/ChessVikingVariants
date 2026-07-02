package com.cccstudio.chess_viking_variants.app.html;

import java.util.Map;

public sealed class HTMLText implements HTMLNode
    permits HTMLJSScript{

    final String text;
    final Map<String, String> attributes;

    public HTMLText(String text, Map<String, String> attributes) {
        this.text = text;
        this.attributes = attributes;
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    @Override
    public String tag() {
        return "p";
    }

    @Override
    public Map<String, String> attributes() {
        return attributes;
    }

    @Override
    public String content() {
        return this.text;
    }

}
