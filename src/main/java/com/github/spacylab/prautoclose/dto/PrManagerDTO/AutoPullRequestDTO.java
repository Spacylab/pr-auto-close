package com.github.spacylab.prautoclose.dto.PrManagerDTO;

import java.util.List;

public class AutoPullRequestDTO {
  private final String accessToken ;
  private final List<String> projectId;
  private final String slackWebhook;

  public AutoPullRequestDTO(String accessToken, List<String> projectId, String slackWebhook) {
    this.accessToken = accessToken;
    this.projectId = projectId;
    this.slackWebhook = slackWebhook;
  }
  public String getAccessToken() {
    return accessToken;
  }
  public List<String> getProjectId() {
    return projectId;
  }
  public String getSlackWebhook() {
    return slackWebhook;
  }
}
