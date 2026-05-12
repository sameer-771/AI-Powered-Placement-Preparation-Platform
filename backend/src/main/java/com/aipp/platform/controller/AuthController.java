package com.aipp.platform.controller;

import com.aipp.platform.dto.ApiResponse;
import com.aipp.platform.dto.AuthLoginRequest;
import com.aipp.platform.dto.AuthRegisterRequest;
import com.aipp.platform.dto.AuthResponse;
import com.aipp.platform.dto.ForgotPasswordRequest;
import com.aipp.platform.dto.ResetPasswordRequest;
import com.aipp.platform.dto.UserProfileDto;
import com.aipp.platform.model.User;
import com.aipp.platform.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody AuthRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthLoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<ApiResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(authService.forgotPassword(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<ApiResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        return ResponseEntity.ok(authService.resetPassword(request));
    }

    @GetMapping("/me")
    public ResponseEntity<UserProfileDto> me() {
        User user = authService.getCurrentUser();
        return ResponseEntity.ok(new UserProfileDto(user.getId(), user.getFullName(), user.getEmail(),
                user.getRole().name()));
    }
}
