package com.cccstudio.chess_viking_variants.app.html;

import java.util.Map;

public final class HTMLJSScript extends HTMLText {

    public HTMLJSScript(String script, Map<String, String> attributes) {
        super(script, attributes);
    }

    @Override
    public String tag() {
        return "script";
    }
}