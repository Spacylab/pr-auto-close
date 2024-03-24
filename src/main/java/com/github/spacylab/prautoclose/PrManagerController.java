package com.github.spacylab.prautoclose;

import com.github.spacylab.prautoclose.dto.PrManagerDTO.PullRequestDTO;
import com.github.spacylab.prautoclose.services.PrManagerService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

@RestController
public class PrManagerController {
    private static final Integer MAX_REMINDER_WEEKS = 4;
    PrManagerService prManagerService;

    public PrManagerController(PrManagerService prManagerService) {
        this.prManagerService = prManagerService;
    }

    @GetMapping(path = "/opened-prs")
    public Object checkPrs(
            @RequestParam(value = "max_weeks", required = false) Integer maxWeeks,
            @RequestParam(value = "accessToken") String accessToken,
            @RequestParam(value = "projectId") String projectId
    ) {
        maxWeeks = maxWeeks == null ? MAX_REMINDER_WEEKS : maxWeeks;
        prManagerService.setAccessToken(accessToken);
        prManagerService.setProjectId(projectId);

        PullRequestDTO[][] weekReminders = IntStream.range(1, maxWeeks).mapToObj(week -> {
            return Arrays.stream(prManagerService.checkPRsFromWeekRange(week, week + 1)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new);
        }).toArray(PullRequestDTO[][]::new);
        var weekRest = Arrays.stream(prManagerService.checkPRsFromWeekRange(maxWeeks)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new);

        var oldPrsByWeek = new HashMap<String, Object>();
        oldPrsByWeek.put("pr_to_be_closed", Arrays.stream(weekRest).map(PullRequestDTO::getAllProperties));
        for (int i = 0; i < weekReminders.length; i++) {
            oldPrsByWeek.put(String.valueOf(i), Arrays.stream(weekReminders[i]).map(PullRequestDTO::getAllProperties));
        }

        return oldPrsByWeek;
    }
}
