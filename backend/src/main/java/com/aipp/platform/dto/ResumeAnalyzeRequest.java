package com.aipp.platform.dto;

import jakarta.validation.constraints.NotBlank;

public class ResumeAnalyzeRequest {
    @NotBlank
    private String resumeText;

    @NotBlank
    private String resumeFilename;

    public String getResumeText() {
        return resumeText;
    }

    public void setResumeText(String resumeText) {
        this.resumeText = resumeText;
    }

    public String getResumeFilename() {
        return resumeFilename;
    }

    public void setResumeFilename(String resumeFilename) {
        this.resumeFilename = resumeFilename;
    }
}
