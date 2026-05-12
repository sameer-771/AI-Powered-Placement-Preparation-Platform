package com.aipp.platform.service;

import com.aipp.platform.dto.LeaderboardEntryDto;
import com.aipp.platform.model.Submission;
import com.aipp.platform.repository.SubmissionRepository;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LeaderboardService {
    private final SubmissionRepository submissionRepository;

    public LeaderboardService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }

    @Transactional(readOnly = true)
    public List<LeaderboardEntryDto> getLeaderboard() {
        Map<Long, LeaderboardEntryDto> aggregate = new HashMap<>();
        for (Submission submission : submissionRepository.findAll()) {
            Long userId = submission.getUser().getId();
            LeaderboardEntryDto entry = aggregate.getOrDefault(userId,
                    new LeaderboardEntryDto(submission.getUser().getFullName(), 0, 0));
            entry.setTotalScore(entry.getTotalScore() + submission.getScore());
            entry.setSubmissions(entry.getSubmissions() + 1);
            aggregate.put(userId, entry);
        }
        return aggregate.values().stream()
                .sorted(Comparator.comparingInt(LeaderboardEntryDto::getTotalScore).reversed())
                .limit(10)
                .toList();
    }
}
