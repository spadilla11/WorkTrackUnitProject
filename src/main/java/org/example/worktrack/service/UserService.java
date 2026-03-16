package org.example.worktrack.service;

import jakarta.transaction.Transactional;
import org.example.worktrack.DTOs.UserDTO;
import org.example.worktrack.DTOs.UserRegistrationDTO;
import org.example.worktrack.entities.User;
import org.example.worktrack.mappers.UserMap;
import org.example.worktrack.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMap userMap;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMap userMap, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.userMap = userMap;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO register (UserRegistrationDTO requestDTO){
        User user = new User();
        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));

        User savedUser = userRepository.save(user);
        return userMap.toDto(savedUser);
    }

    public boolean login(String email, String password){
        return userRepository.findByEmail(email)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    public UserDTO getCurrentUser(){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return userMap.toDto(user);
    }

    public UserDTO updateUser(String firstName, String lastName, String email, String password) {
        String getEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(getEmail).orElseThrow(() -> new RuntimeException("User not found"));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        if (password != null && !password.isEmpty()) {
            user.setPassword(passwordEncoder.encode(password));
        }
        User updatedUser = userRepository.save(user);
        return userMap.toDto(updatedUser);
    }
    public void deleteUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        this.userRepository.delete(user);
    }
}
