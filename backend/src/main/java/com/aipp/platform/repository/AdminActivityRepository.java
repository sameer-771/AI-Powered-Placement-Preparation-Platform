package com.aipp.platform.repository;

import com.aipp.platform.model.AdminActivity;
import com.aipp.platform.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminActivityRepository extends JpaRepository<AdminActivity, Long> {
    List<AdminActivity> findByAdminUserOrderByCreatedAtDesc(User adminUser);
}
