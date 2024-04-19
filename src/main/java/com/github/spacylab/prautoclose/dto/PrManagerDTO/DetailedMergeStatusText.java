package com.github.spacylab.prautoclose.dto.PrManagerDTO;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import com.github.spacylab.prautoclose.dto.GitlabDTO.GitlabDetailedMergeStatus;

public class DetailedMergeStatusText {
  private static final EnumMap<GitlabDetailedMergeStatus, String> DETAILES_MERGE_STATUS_TEXT = new EnumMap<>(
      Map.ofEntries(
          Map.entry(GitlabDetailedMergeStatus.CHECKING, "Git is testing if a valid merge is possible."),
          Map.entry(GitlabDetailedMergeStatus.UNCHECKED, "Git has not yet tested if a valid merge is possible."),
          Map.entry(GitlabDetailedMergeStatus.CI_MUST_PASS, "A CI/CD pipeline must succeed before merge."),
          Map.entry(GitlabDetailedMergeStatus.CI_STILL_RUNNING, "A CI/CD pipeline is still running."),
          Map.entry(GitlabDetailedMergeStatus.MERGEABLE, "The branch can merge cleanly into the target branch."),
          Map.entry(GitlabDetailedMergeStatus.BLOCKED_STATUS, "Blocked by another merge request."),
          Map.entry(GitlabDetailedMergeStatus.DISCUSSIONS_NOT_RESOLVED,
              "All discussions must be resolved before merge."),
          Map.entry(GitlabDetailedMergeStatus.DRAFT_STATUS, "Can’t merge because the merge request is a draft."),
          Map.entry(GitlabDetailedMergeStatus.EXTERNAL_STATUS_CHECKS, "All status checks must pass before merge."),
          Map.entry(GitlabDetailedMergeStatus.NOT_APPROVED, "Approval is required before merge."),
          Map.entry(GitlabDetailedMergeStatus.NOT_OPEN, "The merge request must be open before merge."),
          Map.entry(GitlabDetailedMergeStatus.JIRA_ASSOCIATION_MISSING,
              "The title or description must reference a Jira issue."),
          Map.entry(GitlabDetailedMergeStatus.NEED_REBASE, "The merge request must be rebased."),
          Map.entry(GitlabDetailedMergeStatus.CONFLICT, "There are conflicts between the source and target branches."),
          Map.entry(GitlabDetailedMergeStatus.REQUESTED_CHANGES,
              "The merge request has reviewers who have requested changes.")));

  private DetailedMergeStatusText() {
  }

  public static String getDetailedMergeStatusText(GitlabDetailedMergeStatus detailedMergeStatus) {
    if (DETAILES_MERGE_STATUS_TEXT.size() != GitlabDetailedMergeStatus.values().length) {
      throw new IllegalStateException("Not all detailed merge statuses have a text representation");
    }
    return DETAILES_MERGE_STATUS_TEXT.getOrDefault(detailedMergeStatus, "Unknown status");
  }
}
