package com.aipp.platform.dto;

import java.time.Instant;

public class InterviewFeedbackDto {
    private Long id;
    private String interviewType;
    private String questionsAsked;
    private String aiFeedback;
    private int score;
    private int confidenceRating;
    private Instant createdAt;

    public InterviewFeedbackDto(Long id,
                                String interviewType,
                                String questionsAsked,
                                String aiFeedback,
                                int score,
                                int confidenceRating,
                                Instant createdAt) {
        this.id = id;
        this.interviewType = interviewType;
        this.questionsAsked = questionsAsked;
        this.aiFeedback = aiFeedback;
        this.score = score;
        this.confidenceRating = confidenceRating;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(String interviewType) {
        this.interviewType = interviewType;
    }

    public String getQuestionsAsked() {
        return questionsAsked;
    }

    public void setQuestionsAsked(String questionsAsked) {
        this.questionsAsked = questionsAsked;
    }

    public String getAiFeedback() {
        return aiFeedback;
    }

    public void setAiFeedback(String aiFeedback) {
        this.aiFeedback = aiFeedback;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getConfidenceRating() {
        return confidenceRating;
    }

    public void setConfidenceRating(int confidenceRating) {
        this.confidenceRating = confidenceRating;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
