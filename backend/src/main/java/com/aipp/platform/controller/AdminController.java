package com.aipp.platform.controller;

import com.aipp.platform.dto.AdminActivityDto;
import com.aipp.platform.dto.AdminUserDto;
import com.aipp.platform.model.User;
import com.aipp.platform.service.AdminService;
import com.aipp.platform.service.AuthService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final AdminService adminService;
    private final AuthService authService;

    public AdminController(AdminService adminService, AuthService authService) {
        this.adminService = adminService;
        this.authService = authService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AdminUserDto>> users() {
        return ResponseEntity.ok(adminService.listUsers());
    }

    @GetMapping("/activity")
    public ResponseEntity<List<AdminActivityDto>> activity() {
        User admin = authService.getCurrentUser();
        return ResponseEntity.ok(adminService.listActivity(admin));
    }
}
