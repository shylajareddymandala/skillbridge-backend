package com.skillbridge.skillbridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.skillbridge.entity.CollaborationRequest;
import com.skillbridge.skillbridge.service.CollaborationRequestService;

@RestController
@RequestMapping("/requests")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "https://skillbridge-frontend-xctq.vercel.app"
})
public class CollaborationRequestController {

    private final CollaborationRequestService requestService;

    public CollaborationRequestController(
            CollaborationRequestService requestService) {

        this.requestService = requestService;
    }

    // Send Collaboration Request
    @PostMapping("/send")
    public CollaborationRequest sendRequest(
            @RequestParam Long senderId,
            @RequestParam Long receiverId,
            @RequestParam String message) {

        return requestService.sendRequest(
                senderId,
                receiverId,
                message
        );
    }

    // Get All Requests
    @GetMapping
    public List<CollaborationRequest> getAllRequests() {
        return requestService.getAllRequests();
    }

    // Update Request Status
    @PutMapping("/{requestId}/status")
    public CollaborationRequest updateStatus(
            @PathVariable Long requestId,
            @RequestParam String status) {

        return requestService.updateStatus(requestId, status);
    }

    // Cancel / Delete Collaboration Request
    @DeleteMapping("/{requestId}")
    public String cancelRequest(
            @PathVariable Long requestId) {

        requestService.cancelRequest(requestId);

        return "Collaboration request cancelled successfully";
    }
}