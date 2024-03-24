package com.github.spacylab.prautoclose.dto.GitlabDTO;

public class GitlabTimeStats {
    private Number time_estimate;
    private Number total_time_spent;
    private Number human_time_estimate;
    private Number human_total_time_spent;

    public Number getTimeEstimate() {
        return time_estimate;
    }

    public Number getTotalTimeSpent() {
        return total_time_spent;
    }

    public Number getHumanTimeEstimate() {
        return human_time_estimate;
    }

    public Number getHumanTotalTimeSpent() {
        return human_total_time_spent;
    }
}
