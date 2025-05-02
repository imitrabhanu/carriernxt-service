package com.carriernxt.controller;

import com.carriernxt.dto.LoginRequest;
import com.carriernxt.dto.LoginResponse;
import com.carriernxt.security.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    
    // Hardcoded credentials
    private static final String DEMO_EMAIL = "demo@example.com";
    private static final String DEMO_PASSWORD = "password123";
    private static final String DEMO_NAME = "Demo User";

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // Validate credentials (using hardcoded values for now)
        if (DEMO_EMAIL.equals(loginRequest.getEmail()) && 
            DEMO_PASSWORD.equals(loginRequest.getPassword())) {
            
            // Generate JWT token
            String token = jwtUtil.generateToken(loginRequest.getEmail());
            
            // Create response with token and user info
            LoginResponse response = new LoginResponse(token, loginRequest.getEmail(), DEMO_NAME);
            
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return ResponseEntity.ok().body("Logged out successfully");
    }
    
    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {
        // In a real app, extract user from JWT token. For now, return demo user
        // This endpoint would be protected in a real app
        return ResponseEntity.ok(new UserResponse(DEMO_EMAIL, DEMO_NAME));
    }
    
    public static class UserResponse {
        private String email;
        private String name;
        
        public UserResponse(String email, String name) {
            this.email = email;
            this.name = name;
        }
        
        public String getEmail() {
            return email;
        }
        
        public String getName() {
            return name;
        }
    }
} 