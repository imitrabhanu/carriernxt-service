package com.carriernxt.service;

import com.carriernxt.model.ContactMessage;
import com.carriernxt.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public List<ContactMessage> getAllMessages() {
        return contactRepository.findAll();
    }

    public ContactMessage saveMessage(ContactMessage message) {
        if (message.getCreatedAt() == null) {
            message.setCreatedAt(LocalDateTime.now());
        }
        return contactRepository.save(message);
    }

    public ContactMessage getMessageById(Long id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact message not found with id: " + id));
    }

    public void deleteMessage(Long id) {
        contactRepository.deleteById(id);
    }
} 