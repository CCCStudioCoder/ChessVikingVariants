package com.cccstudio.chess_viking_variants.app.html;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

public sealed class HTMLContainer implements HTMLNode
        permits HTMLDiv {

    public final Collection<HTMLNode> children = new LinkedList<>();
    private final String tag;
    protected final Map<String, String> attributes;

    public HTMLContainer(String tag, Map<String, String> attributes) {
        this.tag = tag;
        this.attributes = attributes;
    }

    public void addChild(HTMLNode child) {
        this.children.add(child);
    }

    public void addAttribute(String key, String value) {
        attributes.put(key, value);
    }

    @Override
    public String tag() {
        return tag;
    }

    @Override
    public Map<String, String> attributes() {
        return attributes;
    }

    @Override
    public String content() {
        return children.stream().map(HTMLNode::render).collect(Collectors.joining());
    }

}