package com.aipp.platform.dto;

import com.aipp.platform.model.InterviewType;
import jakarta.validation.constraints.NotNull;

public class InterviewRequestDto {
    @NotNull
    private InterviewType interviewType;

    private String focusArea;

    private String notes;

    public InterviewType getInterviewType() {
        return interviewType;
    }

    public void setInterviewType(InterviewType interviewType) {
        this.interviewType = interviewType;
    }

    public String getFocusArea() {
        return focusArea;
    }

    public void setFocusArea(String focusArea) {
        this.focusArea = focusArea;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
