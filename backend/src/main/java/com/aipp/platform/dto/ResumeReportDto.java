package com.aipp.platform.dto;

import java.time.Instant;

public class ResumeReportDto {
    private Long id;
    private String resumeFilename;
    private int atsScore;
    private String detectedSkills;
    private String missingSkills;
    private String recommendations;
    private Instant createdAt;

    public ResumeReportDto(Long id,
                           String resumeFilename,
                           int atsScore,
                           String detectedSkills,
                           String missingSkills,
                           String recommendations,
                           Instant createdAt) {
        this.id = id;
        this.resumeFilename = resumeFilename;
        this.atsScore = atsScore;
        this.detectedSkills = detectedSkills;
        this.missingSkills = missingSkills;
        this.recommendations = recommendations;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResumeFilename() {
        return resumeFilename;
    }

    public void setResumeFilename(String resumeFilename) {
        this.resumeFilename = resumeFilename;
    }

    public int getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(int atsScore) {
        this.atsScore = atsScore;
    }

    public String getDetectedSkills() {
        return detectedSkills;
    }

    public void setDetectedSkills(String detectedSkills) {
        this.detectedSkills = detectedSkills;
    }

    public String getMissingSkills() {
        return missingSkills;
    }

    public void setMissingSkills(String missingSkills) {
        this.missingSkills = missingSkills;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
