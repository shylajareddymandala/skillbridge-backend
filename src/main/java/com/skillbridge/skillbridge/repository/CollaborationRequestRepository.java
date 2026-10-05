package com.skillbridge.skillbridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillbridge.skillbridge.entity.CollaborationRequest;

public interface CollaborationRequestRepository
        extends JpaRepository<CollaborationRequest, Long> {

}