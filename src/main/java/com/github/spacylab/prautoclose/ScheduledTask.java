package com.github.spacylab.prautoclose;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {
    @Autowired PrManagerController prManagerController;

    @Scheduled(cron = "0 0 10 * * MON")
    public void executeDevCron() {
        prManagerController.checkPrs();
    }

}
