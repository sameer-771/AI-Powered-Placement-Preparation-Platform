package com.aipp.platform.service;

import com.aipp.platform.dto.AdminActivityDto;
import com.aipp.platform.dto.AdminUserDto;
import com.aipp.platform.model.AdminActivity;
import com.aipp.platform.model.User;
import com.aipp.platform.repository.AdminActivityRepository;
import com.aipp.platform.repository.UserRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    private final UserRepository userRepository;
    private final AdminActivityRepository adminActivityRepository;

    public AdminService(UserRepository userRepository, AdminActivityRepository adminActivityRepository) {
        this.userRepository = userRepository;
        this.adminActivityRepository = adminActivityRepository;
    }

    public List<AdminUserDto> listUsers() {
        return userRepository.findAll().stream()
                .map(user -> new AdminUserDto(user.getId(), user.getFullName(), user.getEmail(),
                        user.getRole().name(), user.isActive(), user.getCreatedAt()))
                .toList();
    }

    public List<AdminActivityDto> listActivity(User admin) {
        return adminActivityRepository.findByAdminUserOrderByCreatedAtDesc(admin).stream()
                .map(activity -> new AdminActivityDto(activity.getId(), activity.getActionType(),
                        activity.getActionDetails(), activity.getCreatedAt()))
                .toList();
    }

    public void logActivity(User admin, String actionType, String details) {
        AdminActivity activity = new AdminActivity();
        activity.setAdminUser(admin);
        activity.setActionType(actionType);
        activity.setActionDetails(details);
        adminActivityRepository.save(activity);
    }
}
