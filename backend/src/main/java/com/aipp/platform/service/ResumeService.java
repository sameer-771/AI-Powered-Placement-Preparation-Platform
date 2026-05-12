package com.aipp.platform.service;

import com.aipp.platform.dto.ResumeAnalyzeRequest;
import com.aipp.platform.dto.ResumeReportDto;
import com.aipp.platform.model.ResumeReport;
import com.aipp.platform.model.User;
import com.aipp.platform.repository.ResumeReportRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ResumeService {
    private final ResumeReportRepository resumeReportRepository;

    public ResumeService(ResumeReportRepository resumeReportRepository) {
        this.resumeReportRepository = resumeReportRepository;
    }

    public ResumeReportDto analyzeResume(User user, ResumeAnalyzeRequest request) {
        List<String> skillPool = List.of("Java", "Spring", "SQL", "Docker", "AWS", "React", "System Design");
        List<String> detected = new ArrayList<>();
        for (String skill : skillPool) {
            if (request.getResumeText().toLowerCase().contains(skill.toLowerCase())) {
                detected.add(skill);
            }
        }
        List<String> missing = new ArrayList<>(skillPool);
        missing.removeAll(detected);
        int score = Math.min(92, 55 + (detected.size() * 6));
        String detectedSkills = String.join(", ", detected);
        String missingSkills = String.join(", ", missing);
        String recommendations = "Add 2 projects and include quantified impact. Strengthen " + missingSkills + ".";

        ResumeReport report = new ResumeReport();
        report.setUser(user);
        report.setResumeFilename(request.getResumeFilename());
        report.setAtsScore(score);
        report.setDetectedSkills(detectedSkills.isBlank() ? "No skills detected" : detectedSkills);
        report.setMissingSkills(missingSkills.isBlank() ? "None" : missingSkills);
        report.setRecommendations(recommendations);
        ResumeReport saved = resumeReportRepository.save(report);

        return new ResumeReportDto(saved.getId(), saved.getResumeFilename(), saved.getAtsScore(),
                saved.getDetectedSkills(), saved.getMissingSkills(), saved.getRecommendations(),
                saved.getCreatedAt());
    }

    public List<ResumeReportDto> history(User user) {
        return resumeReportRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .map(report -> new ResumeReportDto(report.getId(), report.getResumeFilename(), report.getAtsScore(),
                        report.getDetectedSkills(), report.getMissingSkills(), report.getRecommendations(),
                        report.getCreatedAt()))
                .toList();
    }
}
