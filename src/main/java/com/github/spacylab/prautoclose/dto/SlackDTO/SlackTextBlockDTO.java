package com.github.spacylab.prautoclose.dto.SlackDTO;

import java.util.HashMap;
import java.util.Map;

public class SlackTextBlockDTO {
  private String type;
  private String text;

  public SlackTextBlockDTO(String type, String text) {
    this.type = type;
    this.text = text;
  }

  public Map<String, String> getAllProperties() {
    var properties = new HashMap<String, String>();
    properties.put("type", this.type);
    properties.put("text", this.text);
    return properties;
  }

  public String getType() {
    return type;
  }
  
  public String getText() {
    return text;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setText(String text) {
    this.text = text;
  }

}
