package com.carriernxt.controller;

import com.carriernxt.model.Lead;
import com.carriernxt.service.LeadService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {
    
    private final LeadService leadService;

    @GetMapping
    public ResponseEntity<List<Lead>> getAllLeads() {
        return ResponseEntity.ok(leadService.getAllLeads());
    }

    @PostMapping
    public ResponseEntity<Lead> createLead(@RequestBody LeadRequest leadRequest) {
        Lead lead = new Lead();
        lead.setName(leadRequest.getName());
        lead.setEmail(leadRequest.getEmail());
        lead.setPhone(leadRequest.getPhone());
        lead.setEducation(leadRequest.getEducation());
        
        Lead savedLead = leadService.saveLead(lead);
        
        return ResponseEntity.ok(savedLead);
    }

    @Data
    public static class LeadRequest {
        private String name;
        private String email;
        private String phone;
        private String education;
    }
} 