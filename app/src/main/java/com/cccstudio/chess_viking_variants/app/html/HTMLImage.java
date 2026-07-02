package com.cccstudio.chess_viking_variants.app.html;

import java.util.HashMap;
import java.util.Map;

public final class HTMLImage implements HTMLNode {

    Map<String, String> attributes = new HashMap<>();

    public HTMLImage(String src) {
        attributes.put("src", src);
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    @Override
    public String tag() {
        return "img";
    }

    @Override
    public Map<String, String> attributes() {
        return attributes;
    }

    @Override
    public String content() {
        return "";
    }

}