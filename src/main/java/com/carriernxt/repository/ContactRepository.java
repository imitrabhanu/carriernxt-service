package com.carriernxt.repository;

import com.carriernxt.model.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactMessage, Long> {
    // All basic CRUD operations are inherited from JpaRepository
} 