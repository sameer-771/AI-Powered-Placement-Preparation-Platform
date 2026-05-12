package com.aipp.platform.controller;

import com.aipp.platform.dto.InterviewFeedbackDto;
import com.aipp.platform.dto.InterviewRequestDto;
import com.aipp.platform.model.User;
import com.aipp.platform.service.AuthService;
import com.aipp.platform.service.InterviewService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {
    private final InterviewService interviewService;
    private final AuthService authService;

    public InterviewController(InterviewService interviewService, AuthService authService) {
        this.interviewService = interviewService;
        this.authService = authService;
    }

    @PostMapping("/feedback")
    public ResponseEntity<InterviewFeedbackDto> feedback(@Valid @RequestBody InterviewRequestDto request) {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(interviewService.generateFeedback(user, request));
    }

    @GetMapping("/history")
    public ResponseEntity<List<InterviewFeedbackDto>> history() {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(interviewService.history(user));
    }
}
