package com.skillbridge.skillbridge.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skillbridge.skillbridge.entity.Skill;
import com.skillbridge.skillbridge.repository.SkillRepository;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    // Add Skill
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    // Get All Skills
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    // Delete Skill
    public void deleteSkill(Long id) {

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Skill not found"));

        skillRepository.delete(skill);
    }
}