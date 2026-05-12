package com.aipp.platform.controller;

import com.aipp.platform.dto.SubmissionCreateRequest;
import com.aipp.platform.dto.SubmissionResponseDto;
import com.aipp.platform.model.User;
import com.aipp.platform.service.AuthService;
import com.aipp.platform.service.SubmissionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {
    private final SubmissionService submissionService;
    private final AuthService authService;

    public SubmissionController(SubmissionService submissionService, AuthService authService) {
        this.submissionService = submissionService;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<SubmissionResponseDto> create(@Valid @RequestBody SubmissionCreateRequest request) {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(submissionService.createSubmission(user, request));
    }

    @GetMapping
    public ResponseEntity<Page<SubmissionResponseDto>> history(Pageable pageable) {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(submissionService.getHistory(user, pageable));
    }
}
