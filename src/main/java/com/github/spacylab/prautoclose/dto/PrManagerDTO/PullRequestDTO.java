package com.github.spacylab.prautoclose.dto.PrManagerDTO;

import com.github.spacylab.prautoclose.dto.GitlabDTO.GitlabAuthor;
import com.github.spacylab.prautoclose.dto.GitlabDTO.GitlabMergeRequestDTO;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class PullRequestDTO {

    private String created_at;
    private String updated_at;
    private Integer days_untouched;
    private GitlabAuthor author;
    private Number id;
    private Number iid;
    private Number project_id;
    private String title;
    private String description;
    private String web_url;
    private boolean has_conflicts;

    public PullRequestDTO() {}
    public PullRequestDTO(GitlabMergeRequestDTO gitlabMergeRequestDTO) {
        this.created_at = gitlabMergeRequestDTO.getCreated_at();
        this.updated_at = gitlabMergeRequestDTO.getUpdated_at();
        Duration durationLastUpdated = Duration.between(
            ZonedDateTime.ofInstant(
                Instant.parse(gitlabMergeRequestDTO.getUpdated_at()),
                ZoneId.systemDefault()
            ),
            ZonedDateTime.now()
        );
        this.days_untouched = ((Long)durationLastUpdated.toDays()).intValue();
        this.author = gitlabMergeRequestDTO.getAuthor();
        this.id = gitlabMergeRequestDTO.getId();
        this.iid = gitlabMergeRequestDTO.getIid();
        this.project_id = gitlabMergeRequestDTO.getProject_id();
        this.title = gitlabMergeRequestDTO.getTitle();
        this.description = gitlabMergeRequestDTO.getDescription();
        this.web_url = gitlabMergeRequestDTO.getWeb_url();
        this.has_conflicts = gitlabMergeRequestDTO.isHas_conflicts();
    }
    public Object getAllProperties() {
        return new Object[] {
            this.created_at,
            this.updated_at,
            this.days_untouched,
            this.author,
            this.id,
            this.iid,
            this.project_id,
            this.title,
            this.description,
            this.web_url,
            this.has_conflicts,
        };
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
        Duration durationLastUpdated = Duration.between(
                ZonedDateTime.ofInstant(
                        Instant.parse(updated_at),
                        ZoneId.systemDefault()
                ),
                ZonedDateTime.now()
        );
        this.days_untouched = ((Long)durationLastUpdated.toDays()).intValue();
    }

    public Integer getDaysUntouched() {
        return days_untouched;
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
