package com.github.spacylab.prautoclose;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    PrManagerController prManagerController;
    public ScheduledTask(PrManagerController prManagerController) {
        this.prManagerController = prManagerController;
    }

    @Scheduled(cron = "0 0 10 * * MON")
    public void executeDevCron() {
        prManagerController.checkPrs(4, "access_token", "project_id");
    }

}
