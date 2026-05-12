package com.aipp.platform.repository;

import com.aipp.platform.model.Submission;
import com.aipp.platform.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    Page<Submission> findByUserOrderByCreatedAtDesc(User user, Pageable pageable);
    long countByUser(User user);
}
