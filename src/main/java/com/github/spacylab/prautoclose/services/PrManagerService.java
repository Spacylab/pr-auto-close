package com.github.spacylab.prautoclose.services;

import com.github.spacylab.prautoclose.dto.GitlabDTO.GitlabMergeRequestDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class PrManagerService {
    public static final String GITLAB_URL = "https://gitlab.com/api/v4";
    private String accessToken = "";
    private String projectId = "";
    Logger logger = Logger.getLogger(getClass().getName());

    public GitlabMergeRequestDTO[] checkPRsFromWeekRange(Integer startWeek) {
        logger.log(Level.INFO, "Checking PRs... from week {0} ", startWeek);
        return getOpenedPRsFromDateRange(ZonedDateTime.now().minusWeeks(startWeek), ZonedDateTime.ofInstant(Instant.ofEpochSecond(0), ZoneId.systemDefault()));
    }
    public GitlabMergeRequestDTO[] checkPRsFromWeekRange(Integer startWeek, Integer endWeek) {
        logger.log(Level.INFO, "Checking PRs... from week {0} to week {1}", new Object[] {startWeek, endWeek});
        return getOpenedPRsFromDateRange(ZonedDateTime.now().minusWeeks(startWeek), ZonedDateTime.now().minusWeeks(endWeek));
    }

    public GitlabMergeRequestDTO[] getOpenedPRsFromDateRange(ZonedDateTime before, ZonedDateTime after) {
        RestTemplate restTemplate = new RestTemplate();
        var url = GITLAB_URL + "/projects/"+projectId+"/merge_requests";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        var uriTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("updated_before", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(before))
                .queryParam("updated_after", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(after))
                .queryParam("access_token", accessToken)
                .queryParam("state", "opened")
                .queryParam("scope", "all")
                .toUriString();
        logger.log(Level.INFO, uriTemplate);

        ResponseEntity<GitlabMergeRequestDTO[]> result = restTemplate.exchange(
                uriTemplate,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                GitlabMergeRequestDTO[].class
        );
        var mrList = result.getBody();
        assert mrList != null;
        return mrList;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
