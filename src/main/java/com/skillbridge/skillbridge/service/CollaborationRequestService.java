package com.skillbridge.skillbridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillbridge.skillbridge.entity.CollaborationRequest;
import com.skillbridge.skillbridge.entity.User;
import com.skillbridge.skillbridge.repository.CollaborationRequestRepository;
import com.skillbridge.skillbridge.repository.UserRepository;

@Service
public class CollaborationRequestService {

    private final CollaborationRequestRepository requestRepository;
    private final UserRepository userRepository;

    public CollaborationRequestService(
            CollaborationRequestRepository requestRepository,
            UserRepository userRepository) {

        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
    }

    // Send Collaboration Request
    public CollaborationRequest sendRequest(
            Long senderId,
            Long receiverId,
            String message) {

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        CollaborationRequest request = new CollaborationRequest();

        request.setSender(sender);
        request.setReceiver(receiver);
        request.setMessage(message);
        request.setStatus("PENDING");

        return requestRepository.save(request);
    }

    // Get All Collaboration Requests
    public List<CollaborationRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    // Update Request Status
    public CollaborationRequest updateStatus(
            Long requestId,
            String status) {

        CollaborationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        request.setStatus(status);

        return requestRepository.save(request);
    }

    // Cancel / Delete Collaboration Request
    public void cancelRequest(Long requestId) {

        CollaborationRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        requestRepository.delete(request);
    }
}