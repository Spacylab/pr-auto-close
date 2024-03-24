package com.github.spacylab.prautoclose.dto.GitlabDTO;

import org.springframework.http.HttpStatusCode;

public class GitlabException extends RuntimeException {

    private GitlabApi api;
    private HttpStatusCode statusCode;
    private String error;

    public GitlabException(GitlabApi api, HttpStatusCode statusCode, String error) {
        super(error);
        this.api = api;
        this.statusCode = statusCode;
        this.error = error;
        System.out.println("EXCEPTION : " + api + "--" + statusCode + " ---- " + error);
    }
}
