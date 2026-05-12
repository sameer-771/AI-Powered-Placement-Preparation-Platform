package com.aipp.platform.service;

import com.aipp.platform.dto.SubmissionCreateRequest;
import com.aipp.platform.dto.SubmissionResponseDto;
import com.aipp.platform.exception.ResourceNotFoundException;
import com.aipp.platform.model.Question;
import com.aipp.platform.model.Submission;
import com.aipp.platform.model.SubmissionStatus;
import com.aipp.platform.model.User;
import com.aipp.platform.repository.QuestionRepository;
import com.aipp.platform.repository.SubmissionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final QuestionRepository questionRepository;

    public SubmissionService(SubmissionRepository submissionRepository, QuestionRepository questionRepository) {
        this.submissionRepository = submissionRepository;
        this.questionRepository = questionRepository;
    }

    public SubmissionResponseDto createSubmission(User user, SubmissionCreateRequest request) {
        Question question = questionRepository.findById(request.getQuestionId())
                .orElseThrow(() -> new ResourceNotFoundException("Question not found"));
        Submission submission = new Submission();
        submission.setUser(user);
        submission.setQuestion(question);
        submission.setLanguage(request.getLanguage());
        submission.setCodeText(request.getCodeText());
        submission.setTimeTakenSeconds(request.getTimeTakenSeconds());
        int score = Math.min(100, Math.max(30, request.getCodeText().length() / 5));
        SubmissionStatus status = score >= 80 ? SubmissionStatus.PASSED
                : score >= 55 ? SubmissionStatus.PARTIAL : SubmissionStatus.FAILED;
        submission.setScore(score);
        submission.setStatus(status);
        Submission saved = submissionRepository.save(submission);
        return toDto(saved);
    }

    public Page<SubmissionResponseDto> getHistory(User user, Pageable pageable) {
        return submissionRepository.findByUserOrderByCreatedAtDesc(user, pageable)
                .map(this::toDto);
    }

    private SubmissionResponseDto toDto(Submission submission) {
        return new SubmissionResponseDto(submission.getId(), submission.getQuestion().getId(),
                submission.getQuestion().getTitle(), submission.getLanguage(),
                submission.getStatus().name(), submission.getScore(),
                submission.getTimeTakenSeconds(), submission.getCreatedAt());
    }
}
