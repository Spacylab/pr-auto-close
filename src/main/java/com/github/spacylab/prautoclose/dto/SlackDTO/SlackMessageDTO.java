package com.github.spacylab.prautoclose.dto.SlackDTO;

import java.util.List;

public class SlackMessageDTO {
  private List<SlackBlockDTO> blocks;

  public SlackMessageDTO() {
    this.blocks = new java.util.ArrayList<>();
  }
  public SlackMessageDTO( List<SlackBlockDTO> blocks) {
    this.blocks = blocks;
  }
  public Object getAllProperties() {
    var properties = new java.util.HashMap<>();
    properties.put("blocks", this.blocks.stream().map(SlackBlockDTO::getAllProperties).toList());
    return properties;
  }

  public void addBlock(SlackBlockDTO block) {
    this.blocks.add(block);
  }
}
