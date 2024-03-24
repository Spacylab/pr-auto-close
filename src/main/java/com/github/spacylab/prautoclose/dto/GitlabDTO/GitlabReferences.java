package com.github.spacylab.prautoclose.dto.GitlabDTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GitlabReferences {
    @JsonProperty("short")
    private String shortReference;
    private String relative;
    private String full;

    public String getShortKey() {
        return shortReference;
    }

    public String getRelativeKey() {
        return relative;
    }

    public String getFullKey() {
        return full;
    }

}
