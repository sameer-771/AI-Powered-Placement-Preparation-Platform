package com.aipp.platform.dto;

import java.time.Instant;

public class SubmissionResponseDto {
    private Long id;
    private Long questionId;
    private String questionTitle;
    private String language;
    private String status;
    private int score;
    private int timeTakenSeconds;
    private Instant createdAt;

    public SubmissionResponseDto(Long id,
                                 Long questionId,
                                 String questionTitle,
                                 String language,
                                 String status,
                                 int score,
                                 int timeTakenSeconds,
                                 Instant createdAt) {
        this.id = id;
        this.questionId = questionId;
        this.questionTitle = questionTitle;
        this.language = language;
        this.status = status;
        this.score = score;
        this.timeTakenSeconds = timeTakenSeconds;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTimeTakenSeconds() {
        return timeTakenSeconds;
    }

    public void setTimeTakenSeconds(int timeTakenSeconds) {
        this.timeTakenSeconds = timeTakenSeconds;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
