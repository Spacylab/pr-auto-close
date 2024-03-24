package com.github.spacylab.prautoclose.dto.GitlabDTO;

public class GitlabAuthor {
    private Number id;
    private String username;
    private String name;
    private enum state {
        active,
        locked
    }
    private boolean locked;
    private String avatar_url;
    private String web_url;

    public Number getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public boolean isLocked() {
        return locked;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getWeb_url() {
        return web_url;
    }
}
