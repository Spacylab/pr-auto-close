package com.github.spacylab.prautoclose;

import com.github.spacylab.prautoclose.dto.PrManagerDTO.AutoPullRequestDTO;
import com.github.spacylab.prautoclose.dto.PrManagerDTO.PullRequestDTO;
import com.github.spacylab.prautoclose.dto.SlackDTO.SlackBlockDTO;
import com.github.spacylab.prautoclose.dto.SlackDTO.SlackMessageDTO;
import com.github.spacylab.prautoclose.dto.SlackDTO.SlackTextBlockDTO;
import com.github.spacylab.prautoclose.services.PrManagerService;
import com.github.spacylab.prautoclose.services.SlackAPIService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.IntStream;

@RestController
public class PrManagerController {
    Logger logger = Logger.getLogger(getClass().getName());
    @Value("${pr-manager.max-weeks}") private Integer MAX_REMINDER_WEEKS;
    PrManagerService prManagerService;
    SlackAPIService slackAPIService;

    public PrManagerController(PrManagerService prManagerService, SlackAPIService slackAPIService) {
        this.prManagerService = prManagerService;
        this.slackAPIService = slackAPIService;
    }

    @GetMapping(path = "/opened-prs")
    public Map<String, PullRequestDTO[]> checkPrs(
            @RequestParam(value = "max_weeks", required = false) Integer maxWeeks,
            @RequestParam(value = "accessToken") String accessToken,
            @RequestParam(value = "projectId") String projectId
    ) {
        maxWeeks = maxWeeks == null ? MAX_REMINDER_WEEKS : maxWeeks;
        prManagerService.setAccessToken(accessToken);
        prManagerService.setProjectId(projectId);

        PullRequestDTO[][] weekReminders = IntStream.range(1, maxWeeks).mapToObj(week ->
            Arrays.stream(prManagerService.checkPRsFromWeekRange(week, week + 1)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new)
        ).toArray(PullRequestDTO[][]::new);
        var weekRest = Arrays.stream(prManagerService.checkPRsFromWeekRange(maxWeeks)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new);

        var oldPrsByWeek = new HashMap<String, PullRequestDTO[]>();
        oldPrsByWeek.put("prToBeClosed", weekRest);
        for (int i = 0; i < weekReminders.length; i++) {
            oldPrsByWeek.put(String.valueOf(i), weekReminders[i]);
        }

        return oldPrsByWeek;
    }

    @PostMapping(path = "/auto-pr-handling")
    public Object autoPrHandling(
            @RequestBody AutoPullRequestDTO body
    ) {
        var prs = checkPrs(MAX_REMINDER_WEEKS, body.getAccessToken(), body.getProjectId());

        SlackMessageDTO slackMessage = new SlackMessageDTO();
        prs.entrySet().stream().forEach(entry -> {
            if (entry.getValue().length == 0) {return;}

            String headerTxt = entry.getKey().equals("prToBeClosed") 
                ? String.format(":bomb: PR more than %s weeks old :", MAX_REMINDER_WEEKS) 
                : String.format(":hourglass: PR %s weeks old :", Integer.parseInt(entry.getKey()) + 1 );
            SlackBlockDTO headerBlock = new SlackBlockDTO("header", new SlackTextBlockDTO("plain_text", headerTxt));
            slackMessage.addBlock(headerBlock);

            Arrays.stream(entry.getValue()).forEach(pr -> {
                String txt = String.format("<%s|%s> %n %s | Last update : %s days ago", pr.getWeb_url(), pr.getTitle(), pr.getAuthor().getName(), pr.getDaysUntouched());
                SlackBlockDTO sectionBlock = new SlackBlockDTO("section", new SlackTextBlockDTO("mrkdwn", txt));
                slackMessage.addBlock(sectionBlock);
            });

        });

        slackAPIService.setSlackWebhook(body.getSlackWebhook());
        return slackAPIService.sendSlackMessage(slackMessage);
    }
}
