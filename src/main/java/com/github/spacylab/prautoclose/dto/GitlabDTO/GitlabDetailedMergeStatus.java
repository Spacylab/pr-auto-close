package com.github.spacylab.prautoclose.dto.GitlabDTO;

import com.fasterxml.jackson.annotation.JsonValue;

public enum GitlabDetailedMergeStatus {
  BLOCKED_STATUS("blocked_status"),
  CHECKING("checking"),
  UNCHECKED("unchecked"),
  CI_MUST_PASS("ci_must_pass"),
  CI_STILL_RUNNING("ci_still_running"),
  DISCUSSIONS_NOT_RESOLVED("discussions_not_resolved"),
  DRAFT_STATUS("draft_status"),
  EXTERNAL_STATUS_CHECKS("external_status_checks"),
  MERGEABLE("mergeable"),
  NOT_APPROVED("not_approved"),
  NOT_OPEN("not_open"),
  JIRA_ASSOCIATION_MISSING("jira_association_missing"),
  NEED_REBASE("need_rebase"),
  CONFLICT("conflict"),
  REQUESTED_CHANGES("requested_changes");

  private final String value;

  GitlabDetailedMergeStatus(String value) {
    this.value = value;
  }

  @JsonValue
  public String getValue() {
    return this.value;
  }
}
