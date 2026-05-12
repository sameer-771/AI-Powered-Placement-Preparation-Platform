package com.aipp.platform.controller;

import com.aipp.platform.dto.QuestionRequestDto;
import com.aipp.platform.dto.QuestionResponseDto;
import com.aipp.platform.model.Difficulty;
import com.aipp.platform.service.AdminService;
import com.aipp.platform.service.AuthService;
import com.aipp.platform.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {
    private final QuestionService questionService;
    private final AdminService adminService;
    private final AuthService authService;

    public QuestionController(QuestionService questionService, AdminService adminService, AuthService authService) {
        this.questionService = questionService;
        this.adminService = adminService;
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<Page<QuestionResponseDto>> listQuestions(
            @RequestParam(required = false) Difficulty difficulty,
            @RequestParam(required = false) String topic,
            @RequestParam(required = false) String search,
            Pageable pageable) {
        return ResponseEntity.ok(questionService.listQuestions(difficulty, topic, search, pageable));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<QuestionResponseDto> createQuestion(@Valid @RequestBody QuestionRequestDto request) {
        QuestionResponseDto created = questionService.createQuestion(request);
        adminService.logActivity(authService.getCurrentUser(), "QUESTION_CREATE", "Created question " + created.getId());
        return ResponseEntity.ok(created);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponseDto> updateQuestion(@PathVariable Long id,
                                                             @Valid @RequestBody QuestionRequestDto request) {
        QuestionResponseDto updated = questionService.updateQuestion(id, request);
        adminService.logActivity(authService.getCurrentUser(), "QUESTION_UPDATE", "Updated question " + id);
        return ResponseEntity.ok(updated);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        questionService.deleteQuestion(id);
        adminService.logActivity(authService.getCurrentUser(), "QUESTION_DELETE", "Deleted question " + id);
        return ResponseEntity.noContent().build();
    }
}
