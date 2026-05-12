package com.aipp.platform.service;

import com.aipp.platform.dto.ApiResponse;
import com.aipp.platform.dto.AuthLoginRequest;
import com.aipp.platform.dto.AuthRegisterRequest;
import com.aipp.platform.dto.AuthResponse;
import com.aipp.platform.dto.ForgotPasswordRequest;
import com.aipp.platform.dto.ResetPasswordRequest;
import com.aipp.platform.exception.ApiException;
import com.aipp.platform.exception.ResourceNotFoundException;
import com.aipp.platform.model.Role;
import com.aipp.platform.model.User;
import com.aipp.platform.repository.UserRepository;
import com.aipp.platform.security.JwtService;
import com.aipp.platform.security.UserPrincipal;
import java.time.Instant;
import java.util.UUID;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtService jwtService,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(AuthRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ApiException("Email already registered");
        }
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        User saved = userRepository.save(user);
        String token = jwtService.generateToken(new UserPrincipal(saved));
        return new AuthResponse(token, "Bearer", saved.getId(), saved.getFullName(), saved.getEmail(),
                saved.getRole().name());
    }

    public AuthResponse login(AuthLoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        User user = principal.getUser();
        user.setLastLoginAt(Instant.now());
        userRepository.save(user);
        String token = jwtService.generateToken(principal);
        return new AuthResponse(token, "Bearer", user.getId(), user.getFullName(), user.getEmail(),
                user.getRole().name());
    }

    public ApiResponse forgotPassword(ForgotPasswordRequest request) {
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            user.setResetToken(UUID.randomUUID().toString());
            user.setResetTokenExpiresAt(Instant.now().plusSeconds(1800));
            userRepository.save(user);
        });
        return new ApiResponse(true, "If the account exists, a reset link was created");
    }

    public ApiResponse resetPassword(ResetPasswordRequest request) {
        User user = userRepository.findByResetToken(request.getResetToken())
            .orElseThrow(() -> new ResourceNotFoundException("Invalid reset token"));
        if (user.getResetTokenExpiresAt() == null || user.getResetTokenExpiresAt().isBefore(Instant.now())) {
            throw new ApiException("Reset token expired");
        }
        user.setPasswordHash(passwordEncoder.encode(request.getNewPassword()));
        user.setResetToken(null);
        user.setResetTokenExpiresAt(null);
        userRepository.save(user);
        return new ApiResponse(true, "Password updated successfully");
    }

    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !(auth.getPrincipal() instanceof UserPrincipal)) {
            throw new ApiException("Unauthorized");
        }
        return ((UserPrincipal) auth.getPrincipal()).getUser();
    }
}
