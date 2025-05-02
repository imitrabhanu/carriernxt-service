package com.carriernxt.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    // In-memory storage for contact messages (replace with database repository in a real app)
    private final ConcurrentHashMap<Long, ContactMessage> messages = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        return ResponseEntity.ok(new ArrayList<>(messages.values()));
    }

    @PostMapping
    public ResponseEntity<ContactMessage> createMessage(@RequestBody ContactRequest contactRequest) {
        ContactMessage message = new ContactMessage();
        message.setId(idCounter.incrementAndGet());
        message.setName(contactRequest.getName());
        message.setEmail(contactRequest.getEmail());
        message.setSubject(contactRequest.getSubject());
        message.setMessage(contactRequest.getMessage());
        message.setCreatedAt(LocalDateTime.now().toString());
        
        messages.put(message.getId(), message);
        
        return ResponseEntity.ok(message);
    }

    @Data
    public static class ContactRequest {
        private String name;
        private String email;
        private String subject;
        private String message;
    }

    @Data
    public static class ContactMessage {
        private Long id;
        private String name;
        private String email;
        private String subject;
        private String message;
        private String createdAt;
    }
} 