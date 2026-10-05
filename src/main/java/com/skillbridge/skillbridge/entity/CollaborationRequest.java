package com.skillbridge.skillbridge.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "collaboration_requests")
public class CollaborationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Student who sends the request
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    // Student who receives the request
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;

    private String message;

    private String status = "PENDING";

    public CollaborationRequest() {
    }

    public CollaborationRequest(User sender, User receiver,
                                String message, String status) {

        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}