package com.skillbridge.skillbridge.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skillbridge.skillbridge.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Long> {

}