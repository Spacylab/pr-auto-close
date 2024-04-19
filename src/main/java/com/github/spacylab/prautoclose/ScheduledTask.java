package com.github.spacylab.prautoclose;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.spacylab.prautoclose.dto.PrManagerDTO.AutoPullRequestDTO;

@Component
public class ScheduledTask {
    @Value("${pr-manager.access-token}")
    private String ACCESS_TOKEN;
    @Value("${pr-manager.project-ids}")
    private String PROJECT_IDS;
    @Value("${pr-manager.slack-webhook}")
    private String SLACK_WEBHOOK;
    @Value("${pr-manager.trigger-at-startup}")
    private Boolean TRIGGER_AT_STARTUP;

    private final ConfigurableApplicationContext applicationContext;

    PrManagerController prManagerController;

    public ScheduledTask(PrManagerController prManagerController, ConfigurableApplicationContext applicationContext) {
        this.prManagerController = prManagerController;
        this.applicationContext = applicationContext;
    }

    @Scheduled(cron = "${pr-manager.cron.expression}")
    public void executeDevCron() {
        ArrayList<String> projectIdList = new ArrayList<>(Arrays.asList(PROJECT_IDS.split(",")));
        prManagerController.autoPrHandling(new AutoPullRequestDTO(ACCESS_TOKEN, projectIdList, SLACK_WEBHOOK));
    }

    @EventListener(ApplicationReadyEvent.class)
    public void executeAtStartup() {
        if (Boolean.TRUE.equals(TRIGGER_AT_STARTUP)) {
            executeDevCron();
            applicationContext.close();
        }
    }

}
