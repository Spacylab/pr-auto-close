package com.github.spacylab.prautoclose;

import com.github.spacylab.prautoclose.dto.PrManagerDTO.AutoPullRequestDTO;
import com.github.spacylab.prautoclose.dto.PrManagerDTO.PullRequestDTO;
import com.github.spacylab.prautoclose.dto.SlackDTO.SlackBlockDTO;
import com.github.spacylab.prautoclose.dto.SlackDTO.SlackMessageDTO;
import com.github.spacylab.prautoclose.dto.SlackDTO.SlackTextBlockDTO;
import com.github.spacylab.prautoclose.services.GitlabAPIService;
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
    @Value("#{new Boolean('${pr-manager.should-close-prs:false}')}") private Boolean SHOULD_CLOSE_PRS;

    GitlabAPIService gitlabAPIService;
    SlackAPIService slackAPIService;

    public PrManagerController(GitlabAPIService gitlabAPIService, SlackAPIService slackAPIService) {
        this.gitlabAPIService = gitlabAPIService;
        this.slackAPIService = slackAPIService;
    }

    @GetMapping(path = "/opened-prs")
    public Map<String, PullRequestDTO[]> checkPrs(
            @RequestParam(value = "max_weeks", required = false) Integer maxWeeks,
            @RequestParam(value = "accessToken") String accessToken,
            @RequestParam(value = "projectId") String projectId
    ) {
        maxWeeks = maxWeeks == null ? MAX_REMINDER_WEEKS : maxWeeks;
        gitlabAPIService.setAccessToken(accessToken);
        gitlabAPIService.setProjectId(projectId);

        PullRequestDTO[][] weekReminders = IntStream.range(0, maxWeeks + 1).mapToObj(week ->
            Arrays.stream(gitlabAPIService.checkPRsFromWeekRange(week, week + 1)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new)
        ).toArray(PullRequestDTO[][]::new);
        var weekRest = Arrays.stream(gitlabAPIService.checkPRsFromWeekRange(maxWeeks)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new);

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

            String weekWording = "0".equals(entry.getKey()) ? "less than 1" : entry.getKey();
            String headerTxt = entry.getKey().equals("prToBeClosed") 
                ? String.format(":bomb: PR more than %s weeks old :", MAX_REMINDER_WEEKS) 
                : String.format(":hourglass: PR %s weeks old :", weekWording);
            SlackBlockDTO headerBlock = new SlackBlockDTO("header", new SlackTextBlockDTO("plain_text", headerTxt));
            slackMessage.addBlock(headerBlock);

            Arrays.stream(entry.getValue()).forEach(pr -> {
                String txt = String.format("<%s|%s> %n %s | Last update : %s days ago", pr.getWeb_url(), pr.getTitle(), pr.getAuthor().getName(), pr.getDaysUntouched());
                SlackBlockDTO sectionBlock = new SlackBlockDTO("section", new SlackTextBlockDTO("mrkdwn", txt));
                slackMessage.addBlock(sectionBlock);
            });
        });

        if (Boolean.TRUE.equals(SHOULD_CLOSE_PRS)) {
            Arrays.stream(prs.get("prToBeClosed")).forEach(pr -> gitlabAPIService.closePR(pr.getIid().toString())) ;
        }

        slackAPIService.setSlackWebhook(body.getSlackWebhook());

        if (slackMessage.getBlockCount() == 0) {
            Logger.getLogger(getClass().getName()).info("No PRs found!");
            slackMessage.addBlock(new SlackBlockDTO("section", new SlackTextBlockDTO("mrkdwn", ":tada: No PRs found! :tada:")));
        }
        Logger.getLogger(getClass().getName()).info("Sending slack message!");

        return slackAPIService.sendSlackMessage(slackMessage);
    }
}
