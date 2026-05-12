package com.aipp.platform.service;

import com.aipp.platform.dto.InterviewFeedbackDto;
import com.aipp.platform.dto.InterviewRequestDto;
import com.aipp.platform.model.InterviewFeedback;
import com.aipp.platform.model.User;
import com.aipp.platform.repository.InterviewFeedbackRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class InterviewService {
    private final InterviewFeedbackRepository interviewFeedbackRepository;

    public InterviewService(InterviewFeedbackRepository interviewFeedbackRepository) {
        this.interviewFeedbackRepository = interviewFeedbackRepository;
    }

    public InterviewFeedbackDto generateFeedback(User user, InterviewRequestDto request) {
        String questions = "Explain OOP;Design a rate limiter;Optimize SQL joins";
        String feedback = "Strong fundamentals, improve clarity in system design."
                + " Practice time-boxed responses and use metrics.";
        int score = 78;
        int confidence = 82;

        InterviewFeedback feedbackEntity = new InterviewFeedback();
        feedbackEntity.setUser(user);
        feedbackEntity.setInterviewType(request.getInterviewType());
        feedbackEntity.setQuestionsAsked(questions);
        feedbackEntity.setAiFeedback(feedback);
        feedbackEntity.setScore(score);
        feedbackEntity.setConfidenceRating(confidence);
        InterviewFeedback saved = interviewFeedbackRepository.save(feedbackEntity);

        return new InterviewFeedbackDto(saved.getId(), saved.getInterviewType().name(), saved.getQuestionsAsked(),
                saved.getAiFeedback(), saved.getScore(), saved.getConfidenceRating(), saved.getCreatedAt());
    }

    public List<InterviewFeedbackDto> history(User user) {
        return interviewFeedbackRepository.findByUserOrderByCreatedAtDesc(user).stream()
                .map(item -> new InterviewFeedbackDto(item.getId(), item.getInterviewType().name(),
                        item.getQuestionsAsked(), item.getAiFeedback(), item.getScore(),
                        item.getConfidenceRating(), item.getCreatedAt()))
                .toList();
    }
}
