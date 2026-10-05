package com.skillbridge.skillbridge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillbridge.skillbridge.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findBySkillsSkillName(String skillName);

    Optional<User> findByEmail(String email);

}