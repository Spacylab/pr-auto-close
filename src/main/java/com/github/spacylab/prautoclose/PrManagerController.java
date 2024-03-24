package com.github.spacylab.prautoclose;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.spacylab.prautoclose.dto.PrManagerDTO.PullRequestDTO;
import com.github.spacylab.prautoclose.services.PrManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

@RestController
public class PrManagerController {
    private final Integer MAX_REMINDER_WEEKS = 4;
    @Autowired
    PrManagerService prManagerService;

//    @GetMapping(path= "/close-pr")
//    public GitlabAuthor[] closePr() {
//        System.out.println("Getting old PRs...");
//        var oldPrs = prManagerService.getOldPRs();
//        System.out.println("Old PRs: " + Arrays.toString(oldPrs));
//        return Arrays.stream(oldPrs).map(GitlabMergeRequestDTO::getAuthor).toArray(GitlabAuthor[]::new);
//    }

//    @PostMapping(path= "/reminders")
//    public String[] weeklyReminders(@RequestBody WeeklyReminderDTO weeklyReminderDTO) {
//        var selectedWeek = weeklyReminderDTO.getWeek();
//        System.out.println("Checking PRs for week: " + selectedWeek);
//        var PRs = prManagerService.getOpenedPRsFromDateRange(
//                ZonedDateTime.now().minusWeeks(selectedWeek),
//                ZonedDateTime.now().minusWeeks(selectedWeek + 1)
//        );
//        return Arrays.stream(PRs).map(GitlabMergeRequestDTO::getTitle).toArray(String[]::new);
//    }

    @GetMapping(path= "/check-prs")
    public Object checkPrs() {
        //PullRequestDTO[] weekReminders = IntStream.range(1, MAX_REMINDER_WEEKS).map(week -> {
        //    return Arrays.stream(prManagerService.checkPRsFromWeekRange(week - 1 , week)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new)
        //});
        PullRequestDTO[] weekReminders = IntStream.range(1, MAX_REMINDER_WEEKS).mapToObj(week -> {
            return Arrays.stream(prManagerService.checkPRsFromWeekRange(week - 1 , week)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new);
        }).reduce(new PullRequestDTO[0], (acc, prs) -> {
            return Arrays.copyOf(acc, acc.length + prs.length);
        });

        // var week1 = Arrays.stream(prManagerService.checkPRsFromWeekRange(1, 2)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new);
        // var week2 = Arrays.stream(prManagerService.checkPRsFromWeekRange(2, 3)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new);
        // var week3 = Arrays.stream(prManagerService.checkPRsFromWeekRange(3, 4)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new);
        var weekRest = Arrays.stream(prManagerService.checkPRsFromWeekRange(MAX_REMINDER_WEEKS)).map(PullRequestDTO::new).toArray(PullRequestDTO[]::new);
        var oldPrsByWeek = new HashMap<String, Object>() {{
            put("pr_to_be_closed", Arrays.stream(weekRest).map(PullRequestDTO::getAllProperties));
        }};
        for (int i = 0; i < MAX_REMINDER_WEEKS; i++) {
            oldPrsByWeek.put("week_" + (i + 1), Arrays.stream(weekReminders).skip(i).limit(1).map(PullRequestDTO::getAllProperties));
        }
        System.out.println(oldPrsByWeek);

        return oldPrsByWeek;
//        return new HashMap<String, Object>() {{
//            putAll(Arrays.stream(weekReminders).map(PullRequestDTO::getAllProperties));
//            put("week_1", Arrays.stream(week1).map(PullRequestDTO::getAllProperties));
//            put("week_2", Arrays.stream(week2).map(PullRequestDTO::getAllProperties));
//            put("week_3", Arrays.stream(week3).map(PullRequestDTO::getAllProperties));
//            put("pr_to_be_closed", Arrays.stream(weekRest).map(PullRequestDTO::getAllProperties));
//        }};
    }
}
