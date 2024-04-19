package com.github.spacylab.prautoclose.dto.GitlabDTO;

public class GitlabMergeRequestDTO {
    private Number id;
    private Number iid;
    private Number project_id;
    private String title;
    private String description;

    private enum state {
        opened,
        closed,
        locked,
        merged
    }

    private String created_at;
    private String updated_at;
    private GitlabAuthor merged_by;
    private String merged_at;
    private String closed_at;
    private String target_branch;
    private String source_branch;
    private Number user_notes_count;
    private Number upvotes;
    private Number downvotes;
    private GitlabAuthor author;
    private GitlabAuthor[] assignees;
    private GitlabAuthor assignee;
    private GitlabAuthor[] reviewers;
    private Number source_project_id;
    private Number target_project_id;
    private String[] labels;
    private boolean draft;
    private boolean work_in_progress;
    private String milestone;
    private boolean merge_when_pipeline_succeeds;
    private String merge_status;
    private GitlabDetailedMergeStatus detailed_merge_status;
    private String sha;
    private String merge_commit_sha;
    private String squash_commit_sha;
    private boolean discussion_locked;
    private boolean should_remove_source_branch;
    private boolean force_remove_source_branch;
    private String prepared_at;
    private String reference;
    private GitlabReferences references;
    private String web_url;
    private GitlabTimeStats time_stats;
    private boolean squash;
    private boolean squash_on_merge;
    private GitlabTaskCompletionStatus task_completion_status;
    private boolean has_conflicts;
    private boolean blocking_discussions_resolved;
    private Number approvals_before_merge;

    public Number getId() {
        return id;
    }

    public Number getIid() {
        return iid;
    }

    public Number getProject_id() {
        return project_id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public GitlabAuthor getMerged_by() {
        return merged_by;
    }

    public String getMerged_at() {
        return merged_at;
    }

    public String getClosed_at() {
        return closed_at;
    }

    public String getTarget_branch() {
        return target_branch;
    }

    public String getSource_branch() {
        return source_branch;
    }

    public Number getUser_notes_count() {
        return user_notes_count;
    }

    public Number getUpvotes() {
        return upvotes;
    }

    public Number getDownvotes() {
        return downvotes;
    }

    public GitlabAuthor getAuthor() {
        return author;
    }

    public GitlabAuthor[] getAssignees() {
        return assignees;
    }

    public GitlabAuthor getAssignee() {
        return assignee;
    }

    public GitlabAuthor[] getReviewers() {
        return reviewers;
    }

    public Number getSource_project_id() {
        return source_project_id;
    }

    public Number getTarget_project_id() {
        return target_project_id;
    }

    public String[] getLabels() {
        return labels;
    }

    public boolean isDraft() {
        return draft;
    }

    public boolean isWork_in_progress() {
        return work_in_progress;
    }

    public String getMilestone() {
        return milestone;
    }

    public boolean isMerge_when_pipeline_succeeds() {
        return merge_when_pipeline_succeeds;
    }

    public String getMerge_status() {
        return merge_status;
    }

    public GitlabDetailedMergeStatus getDetailed_merge_status() {
        return detailed_merge_status;
    }

    public String getSha() {
        return sha;
    }

    public String getMerge_commit_sha() {
        return merge_commit_sha;
    }

    public String getSquash_commit_sha() {
        return squash_commit_sha;
    }

    public boolean isDiscussion_locked() {
        return discussion_locked;
    }

    public boolean isShould_remove_source_branch() {
        return should_remove_source_branch;
    }

    public boolean isForce_remove_source_branch() {
        return force_remove_source_branch;
    }

    public String getPrepared_at() {
        return prepared_at;
    }

    public String getReference() {
        return reference;
    }

    public GitlabReferences getReferences() {
        return references;
    }

    public String getWeb_url() {
        return web_url;
    }

    public GitlabTimeStats getTime_stats() {
        return time_stats;
    }

    public boolean isSquash() {
        return squash;
    }

    public boolean isSquash_on_merge() {
        return squash_on_merge;
    }

    public GitlabTaskCompletionStatus getTask_completion_status() {
        return task_completion_status;
    }

    public boolean isHas_conflicts() {
        return has_conflicts;
    }

    public boolean isBlocking_discussions_resolved() {
        return blocking_discussions_resolved;
    }

    public Number getApprovals_before_merge() {
        return approvals_before_merge;
    }
}
