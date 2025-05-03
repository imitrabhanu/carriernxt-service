package com.carriernxt.controller;

import com.carriernxt.model.ContactMessage;
import com.carriernxt.service.ContactService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/contact")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping
    public ResponseEntity<List<ContactMessage>> getAllMessages() {
        return ResponseEntity.ok(contactService.getAllMessages());
    }

    @PostMapping
    public ResponseEntity<ContactMessage> createMessage(@RequestBody ContactRequest contactRequest) {
        ContactMessage message = new ContactMessage();
        message.setName(contactRequest.getName());
        message.setEmail(contactRequest.getEmail());
        message.setSubject(contactRequest.getSubject());
        message.setMessage(contactRequest.getMessage());
        message.setCreatedAt(LocalDateTime.now());
        
        return ResponseEntity.ok(contactService.saveMessage(message));
    }

    @Data
    public static class ContactRequest {
        private String name;
        private String email;
        private String subject;
        private String message;
    }
} 