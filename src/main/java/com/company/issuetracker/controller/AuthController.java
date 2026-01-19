package com.company.issuetracker.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.company.issuetracker.repository.UserRepository;
import com.company.issuetracker.security.JwtUtil;
import com.company.issuetracker.entity.User;
import com.company.issuetracker.dto.LoginRequest;
import com.company.issuetracker.dto.UserRegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthController(UserRepository repo,
            PasswordEncoder encoder,
            JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    // 🔐 LOGIN
    @PostMapping("/login")
    public ResponseEntity<String> login(
            @RequestBody LoginRequest request) {

        User user = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getEmail());
        return ResponseEntity.ok(token);
    }

    // 🆕 REGISTER
    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody UserRegisterRequest request) {

        if (repo.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email already registered");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));

        repo.save(user);

        return ResponseEntity.ok("User registered successfully");
    }
}
