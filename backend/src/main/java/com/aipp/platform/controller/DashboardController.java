package com.aipp.platform.controller;

import com.aipp.platform.dto.DashboardSummaryDto;
import com.aipp.platform.model.User;
import com.aipp.platform.service.AuthService;
import com.aipp.platform.service.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    private final DashboardService dashboardService;
    private final AuthService authService;

    public DashboardController(DashboardService dashboardService, AuthService authService) {
        this.dashboardService = dashboardService;
        this.authService = authService;
    }

    @GetMapping("/summary")
    public ResponseEntity<DashboardSummaryDto> summary() {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(dashboardService.buildSummary(user));
    }
}
