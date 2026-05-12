package com.aipp.platform.dto;

public class LeaderboardEntryDto {
    private String fullName;
    private int totalScore;
    private int submissions;

    public LeaderboardEntryDto(String fullName, int totalScore, int submissions) {
        this.fullName = fullName;
        this.totalScore = totalScore;
        this.submissions = submissions;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getSubmissions() {
        return submissions;
    }

    public void setSubmissions(int submissions) {
        this.submissions = submissions;
    }
}
