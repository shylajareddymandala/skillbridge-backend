package com.skillbridge.skillbridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.skillbridge.entity.Skill;
import com.skillbridge.skillbridge.service.SkillService;

@RestController
@RequestMapping("/skills")
@CrossOrigin(origins = "http://localhost:5173")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    // Add Skill
    @PostMapping
    public Skill addSkill(@RequestBody Skill skill) {
        return skillService.addSkill(skill);
    }

    // Get All Skills
    @GetMapping
    public List<Skill> getAllSkills() {
        return skillService.getAllSkills();
    }

    // Delete Skill
    @DeleteMapping("/{id}")
    public String deleteSkill(@PathVariable Long id) {

        skillService.deleteSkill(id);

        return "Skill deleted successfully";
    }
}