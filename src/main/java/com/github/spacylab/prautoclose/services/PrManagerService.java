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


@Service
public class PrManagerService {
    public final String GITLAB_URL = "https://gitlab.com/api/v4";
    public GitlabMergeRequestDTO[] checkPRsFromWeekRange(Integer startWeek) {
        System.out.println("Checking PRs... from week " + startWeek);
        return getOpenedPRsFromDateRange(ZonedDateTime.now().minusWeeks(startWeek), ZonedDateTime.ofInstant(Instant.ofEpochSecond(0), ZoneId.systemDefault()));
    }
    public GitlabMergeRequestDTO[] checkPRsFromWeekRange(Integer startWeek, Integer endWeek) {
        System.out.println("Checking PRs... from week " + startWeek + " to week " + endWeek + " ago.");
        return getOpenedPRsFromDateRange(ZonedDateTime.now().minusWeeks(startWeek), ZonedDateTime.now().minusWeeks(endWeek));
    }

    public GitlabMergeRequestDTO[] getOpenedPRsFromDateRange(ZonedDateTime before, ZonedDateTime after) {
        RestTemplate restTemplate = new RestTemplate();
        var url = GITLAB_URL + "/projects/33560803/merge_requests";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        var uriTemplate = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("updated_before", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(before))
                .queryParam("updated_after", DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(after))
                .queryParam("access_token", "glpat-N5QdyTFzQz9-agM1CvN_")
                .queryParam("state", "opened")
                .queryParam("scope", "all")
                .toUriString();
        System.out.println(uriTemplate);

        ResponseEntity<GitlabMergeRequestDTO[]> result = restTemplate.exchange(
                uriTemplate,
                HttpMethod.GET,
                new HttpEntity<>(headers),
                GitlabMergeRequestDTO[].class
        );
        var MRs = result.getBody();
        assert MRs != null;
        return MRs;
    }
}
