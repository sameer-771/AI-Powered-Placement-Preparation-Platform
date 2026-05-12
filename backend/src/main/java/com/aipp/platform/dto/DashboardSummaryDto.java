package com.aipp.platform.dto;

import java.util.List;

public class DashboardSummaryDto {
    private String greetingName;
    private int totalSolved;
    private int totalQuestions;
    private int streakDays;
    private int weeklyGoal;
    private int weeklyProgress;
    private List<String> weeklyLabels;
    private List<Integer> weeklyActivity;
    private List<Integer> difficultySplit;
    private List<String> recentActivities;

    public DashboardSummaryDto(String greetingName,
                               int totalSolved,
                               int totalQuestions,
                               int streakDays,
                               int weeklyGoal,
                               int weeklyProgress,
                               List<String> weeklyLabels,
                               List<Integer> weeklyActivity,
                               List<Integer> difficultySplit,
                               List<String> recentActivities) {
        this.greetingName = greetingName;
        this.totalSolved = totalSolved;
        this.totalQuestions = totalQuestions;
        this.streakDays = streakDays;
        this.weeklyGoal = weeklyGoal;
        this.weeklyProgress = weeklyProgress;
        this.weeklyLabels = weeklyLabels;
        this.weeklyActivity = weeklyActivity;
        this.difficultySplit = difficultySplit;
        this.recentActivities = recentActivities;
    }

    public String getGreetingName() {
        return greetingName;
    }

    public void setGreetingName(String greetingName) {
        this.greetingName = greetingName;
    }

    public int getTotalSolved() {
        return totalSolved;
    }

    public void setTotalSolved(int totalSolved) {
        this.totalSolved = totalSolved;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public int getStreakDays() {
        return streakDays;
    }

    public void setStreakDays(int streakDays) {
        this.streakDays = streakDays;
    }

    public int getWeeklyGoal() {
        return weeklyGoal;
    }

    public void setWeeklyGoal(int weeklyGoal) {
        this.weeklyGoal = weeklyGoal;
    }

    public int getWeeklyProgress() {
        return weeklyProgress;
    }

    public void setWeeklyProgress(int weeklyProgress) {
        this.weeklyProgress = weeklyProgress;
    }

    public List<String> getWeeklyLabels() {
        return weeklyLabels;
    }

    public void setWeeklyLabels(List<String> weeklyLabels) {
        this.weeklyLabels = weeklyLabels;
    }

    public List<Integer> getWeeklyActivity() {
        return weeklyActivity;
    }

    public void setWeeklyActivity(List<Integer> weeklyActivity) {
        this.weeklyActivity = weeklyActivity;
    }

    public List<Integer> getDifficultySplit() {
        return difficultySplit;
    }

    public void setDifficultySplit(List<Integer> difficultySplit) {
        this.difficultySplit = difficultySplit;
    }

    public List<String> getRecentActivities() {
        return recentActivities;
    }

    public void setRecentActivities(List<String> recentActivities) {
        this.recentActivities = recentActivities;
    }
}
