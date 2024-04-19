package com.github.spacylab.prautoclose.dto.PrManagerDTO;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PullRequestLastUpdate {
  private String createdAt;
  private String updatedAt;

  public PullRequestLastUpdate() {
  }

  public PullRequestLastUpdate(String createdAt, String updatedAt) {
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getLastUpdateText() {
    Duration durationLastUpdated = Duration.between(
        ZonedDateTime.ofInstant(
            Instant.parse(this.updatedAt),
            ZoneId.systemDefault()),
        ZonedDateTime.now());
    Integer daysUntouched = ((Long) durationLastUpdated.toDays()).intValue();
    if (daysUntouched > 0) {
      return daysUntouched + " days ago";
    }
    Integer hoursUntouched = ((Long) durationLastUpdated.toHours()).intValue();
    if (hoursUntouched > 0) {
      return hoursUntouched + " hours ago";
    }
    Integer minutesUntouched = ((Long) durationLastUpdated.toMinutes()).intValue();
    if (minutesUntouched > 0) {
      return minutesUntouched + " minutes ago";
    }
    Integer secondsUntouched = ((Long) durationLastUpdated.getSeconds()).intValue();
    return secondsUntouched + " seconds ago";
  }

  public String getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(String createdAt) {
    this.createdAt = createdAt;
  }

  public String getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(String updatedAt) {
    this.updatedAt = updatedAt;
  }
}
