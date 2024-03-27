package com.github.spacylab.prautoclose;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.spacylab.prautoclose.dto.PrManagerDTO.AutoPullRequestDTO;

@Component
public class ScheduledTask {
    @Value("${pr-manager.access-token}") private String ACCESS_TOKEN;
    @Value("${pr-manager.project-ids}") private String PROJECT_IDS;
    @Value("${pr-manager.slack-webhook}") private String SLACK_WEBHOOK;

    PrManagerController prManagerController;
    public ScheduledTask(PrManagerController prManagerController) {
        this.prManagerController = prManagerController;
    }

    @Scheduled(cron = "${pr-manager.cron.expression}")
    public void executeDevCron() {
        ArrayList<String> projectIdList = new ArrayList<>(Arrays.asList(PROJECT_IDS.split(",")));
        projectIdList.forEach(projectId -> prManagerController.autoPrHandling(new AutoPullRequestDTO(ACCESS_TOKEN, projectId, SLACK_WEBHOOK)));
    }

}
