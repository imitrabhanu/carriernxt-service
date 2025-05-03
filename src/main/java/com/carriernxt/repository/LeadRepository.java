package com.carriernxt.repository;

import com.carriernxt.model.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Long> {
    // Custom queries can be added here if needed
} 