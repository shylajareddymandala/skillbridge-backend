package com.skillbridge.skillbridge.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.skillbridge.skillbridge.entity.Skill;
import com.skillbridge.skillbridge.entity.User;
import com.skillbridge.skillbridge.repository.SkillRepository;
import com.skillbridge.skillbridge.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    private final BCryptPasswordEncoder passwordEncoder =
            new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository,
                       SkillRepository skillRepository) {

        this.userRepository = userRepository;
        this.skillRepository = skillRepository;
    }

    // Create User
    public User createUser(User user) {

        String encryptedPassword =
                passwordEncoder.encode(user.getPassword());

        user.setPassword(encryptedPassword);

        return userRepository.save(user);
    }

    // Get All Users
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    // Get User by ID
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));
    }

    // Login User
    public User loginUser(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String storedPassword = user.getPassword();

        // Check if the password is already BCrypt encrypted
        if (storedPassword.startsWith("$2a$")
                || storedPassword.startsWith("$2b$")
                || storedPassword.startsWith("$2y$")) {

            if (!passwordEncoder.matches(password, storedPassword)) {

                throw new RuntimeException("Invalid password");
            }

        } else {

            // Support old plain-text passwords
            if (!storedPassword.equals(password)) {

                throw new RuntimeException("Invalid password");
            }

            // Convert old password to BCrypt
            user.setPassword(
                    passwordEncoder.encode(password)
            );

            userRepository.save(user);
        }

        return user;
    }

    // Search Users by Skill
    public List<User> searchUsersBySkill(String skillName) {

        return userRepository.findBySkillsSkillName(skillName);
    }

    // Update User
    public User updateUser(Long id, User updatedUser) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setName(updatedUser.getName());

        user.setEmail(updatedUser.getEmail());

        // Encrypt password only if a new password is provided
        if (updatedUser.getPassword() != null
                && !updatedUser.getPassword().isBlank()) {

            user.setPassword(
                    passwordEncoder.encode(
                            updatedUser.getPassword()
                    )
            );
        }

        user.setDepartment(updatedUser.getDepartment());

        user.setRole(updatedUser.getRole());

        return userRepository.save(user);
    }

    // Delete User
    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        userRepository.delete(user);
    }

    // Assign Skill to User
    public User assignSkill(Long userId, Long skillId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        Skill skill = skillRepository.findById(skillId)
                .orElseThrow(() ->
                        new RuntimeException("Skill not found"));

        user.getSkills().add(skill);

        return userRepository.save(user);
    }
}