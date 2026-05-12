package com.aipp.platform.repository;

import com.aipp.platform.model.ResumeReport;
import com.aipp.platform.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeReportRepository extends JpaRepository<ResumeReport, Long> {
    List<ResumeReport> findByUserOrderByCreatedAtDesc(User user);
}
