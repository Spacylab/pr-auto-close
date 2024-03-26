package com.github.spacylab.prautoclose.dto.PrManagerDTO;

public class AutoPullRequestDTO {
  private final String accessToken ;
  private final String projectId;
  private final String slackWebhook;

  public AutoPullRequestDTO(String accessToken, String projectId, String slackWebhook) {
    this.accessToken = accessToken;
    this.projectId = projectId;
    this.slackWebhook = slackWebhook;
  }
  public String getAccessToken() {
    return accessToken;
  }
  public String getProjectId() {
    return projectId;
  }
  public String getSlackWebhook() {
    return slackWebhook;
  }
}
