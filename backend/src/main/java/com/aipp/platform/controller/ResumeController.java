package com.aipp.platform.controller;

import com.aipp.platform.dto.ResumeAnalyzeRequest;
import com.aipp.platform.dto.ResumeReportDto;
import com.aipp.platform.model.User;
import com.aipp.platform.service.AuthService;
import com.aipp.platform.service.ResumeService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {
    private final ResumeService resumeService;
    private final AuthService authService;

    public ResumeController(ResumeService resumeService, AuthService authService) {
        this.resumeService = resumeService;
        this.authService = authService;
    }

    @PostMapping("/analyze")
    public ResponseEntity<ResumeReportDto> analyze(@Valid @RequestBody ResumeAnalyzeRequest request) {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(resumeService.analyzeResume(user, request));
    }

    @GetMapping("/history")
    public ResponseEntity<List<ResumeReportDto>> history() {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(resumeService.history(user));
    }
}
