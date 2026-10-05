package com.skillbridge.skillbridge.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skillbridge.skillbridge.entity.User;
import com.skillbridge.skillbridge.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Create User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Login User
    @PostMapping("/login")
    public User loginUser(
            @RequestParam String email,
            @RequestParam String password) {

        return userService.loginUser(email, password);
    }

    // Get All Users
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Get User by ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Search Users by Skill
    @GetMapping("/search")
    public List<User> searchUsersBySkill(
            @RequestParam String skillName) {

        return userService.searchUsersBySkill(skillName);
    }

    // Update User
    @PutMapping("/{id}")
    public User updateUser(
            @PathVariable Long id,
            @RequestBody User user) {

        return userService.updateUser(id, user);
    }

    // Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.deleteUser(id);
        return "User deleted successfully";
    }

    // Assign Skill to User
    @PostMapping("/{userId}/skills/{skillId}")
    public User assignSkill(
            @PathVariable Long userId,
            @PathVariable Long skillId) {

        return userService.assignSkill(userId, skillId);
    }
}