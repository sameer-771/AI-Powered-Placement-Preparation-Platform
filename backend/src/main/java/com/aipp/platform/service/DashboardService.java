package com.aipp.platform.service;

import com.aipp.platform.dto.DashboardSummaryDto;
import com.aipp.platform.model.User;
import com.aipp.platform.repository.QuestionRepository;
import com.aipp.platform.repository.SubmissionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {
    private final SubmissionRepository submissionRepository;
    private final QuestionRepository questionRepository;

    public DashboardService(SubmissionRepository submissionRepository, QuestionRepository questionRepository) {
        this.submissionRepository = submissionRepository;
        this.questionRepository = questionRepository;
    }

    public DashboardSummaryDto buildSummary(User user) {
        long totalQuestions = questionRepository.count();
        long totalSolved = submissionRepository.countByUser(user);
        int weeklyGoal = 12;
        int weeklyProgress = (int) Math.min(totalSolved, weeklyGoal);
        int streakDays = Math.max(1, (int) (totalSolved % 7));
        List<String> labels = List.of("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun");
        List<Integer> activity = List.of(2, 4, 1, 3, 2, 5, 1);
        List<Integer> difficultySplit = List.of(4, 3, 1);
        List<String> recentActivities = List.of(
                "Solved Two Sum",
                "Resume score improved to 72",
                "Mock interview completed");
        return new DashboardSummaryDto(user.getFullName(), (int) totalSolved, (int) totalQuestions,
                streakDays, weeklyGoal, weeklyProgress, labels, activity, difficultySplit, recentActivities);
    }
}
