package com.github.spacylab.prautoclose.dto.PrManagerDTO;

import com.github.spacylab.prautoclose.dto.GitlabDTO.GitlabAuthor;
import com.github.spacylab.prautoclose.dto.GitlabDTO.GitlabDetailedMergeStatus;
import com.github.spacylab.prautoclose.dto.GitlabDTO.GitlabMergeRequestDTO;

public class PullRequestDTO {

    private String created_at;
    private String updated_at;
    private GitlabAuthor author;
    private Number id;
    private Number iid;
    private Number project_id;
    private String title;
    private String description;
    private String web_url;
    private boolean has_conflicts;
    private GitlabDetailedMergeStatus detailedMergeStatus;

    public PullRequestDTO() {
    }

    public PullRequestDTO(GitlabMergeRequestDTO gitlabMergeRequestDTO) {
        this.created_at = gitlabMergeRequestDTO.getCreated_at();
        this.updated_at = gitlabMergeRequestDTO.getUpdated_at();
        this.author = gitlabMergeRequestDTO.getAuthor();
        this.id = gitlabMergeRequestDTO.getId();
        this.iid = gitlabMergeRequestDTO.getIid();
        this.project_id = gitlabMergeRequestDTO.getProject_id();
        this.title = gitlabMergeRequestDTO.getTitle();
        this.description = gitlabMergeRequestDTO.getDescription();
        this.web_url = gitlabMergeRequestDTO.getWeb_url();
        this.has_conflicts = gitlabMergeRequestDTO.isHas_conflicts();
        this.detailedMergeStatus = gitlabMergeRequestDTO.getDetailed_merge_status();
    }

    public GitlabDetailedMergeStatus getDetailedMergeStatus() {
        return detailedMergeStatus;
    }

    public String getDetailedMergeStatusText() {
        return DetailedMergeStatusText.getDetailedMergeStatusText(detailedMergeStatus);
    }

    public String getLastUpdateText() {
        return new PullRequestLastUpdate(this.created_at, this.updated_at).getLastUpdateText();
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public GitlabAuthor getAuthor() {
        return author;
    }

    public void setAuthor(GitlabAuthor author) {
        this.author = author;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Number getIid() {
        return iid;
    }

    public void setIid(Number iid) {
        this.iid = iid;
    }

    public Number getProject_id() {
        return project_id;
    }

    public void setProject_id(Number project_id) {
        this.project_id = project_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }

    public boolean isHas_conflicts() {
        return has_conflicts;
    }

    public void setHas_conflicts(boolean has_conflicts) {
        this.has_conflicts = has_conflicts;
    }
}
