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
@RequestMapping("/api/leads")
public class LeadController {

    // In-memory storage for leads (replace with database repository in a real app)
    private final ConcurrentHashMap<Long, Lead> leads = new ConcurrentHashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @GetMapping
    public ResponseEntity<List<Lead>> getAllLeads() {
        return ResponseEntity.ok(new ArrayList<>(leads.values()));
    }

    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody LeadRequest leadRequest) {
        Lead lead = new Lead();
        lead.setId(idCounter.incrementAndGet());
        lead.setName(leadRequest.getName());
        lead.setEmail(leadRequest.getEmail());
        lead.setPhone(leadRequest.getPhone());
        lead.setEducation(leadRequest.getEducation());
        lead.setCreatedAt(LocalDateTime.now().toString());
        
        leads.put(lead.getId(), lead);
        
        return ResponseEntity.ok(lead);
    }

    @Data
    public static class LeadRequest {
        private String name;
        private String email;
        private String phone;
        private String education;
    }

    @Data
    public static class Lead {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private String education;
        private String createdAt;
    }
} 