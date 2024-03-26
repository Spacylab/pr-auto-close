package com.github.spacylab.prautoclose.dto.SlackDTO;

public class SlackBlockDTO {
    private String type;
    private SlackTextBlockDTO textBlock;

    public SlackBlockDTO(String type, SlackTextBlockDTO textBlock) {
        this.type = type;
        this.textBlock = textBlock;
    }

    public Object getAllProperties() {
        var properties = new java.util.HashMap<>();
        properties.put("type", this.type);
        properties.put("text", this.textBlock.getAllProperties());
        return properties;
    }

    public String getType() {
        return type;
    }

    public SlackTextBlockDTO getTextBlock() {
        return textBlock;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTextBlock(SlackTextBlockDTO textBlock) {
        this.textBlock = textBlock;
    }
}
