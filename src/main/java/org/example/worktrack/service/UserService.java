package org.example.worktrack.service;

import jakarta.transaction.Transactional;
import org.example.worktrack.DTOs.UserDTO;
import org.example.worktrack.entities.User;
import org.example.worktrack.mappers.UserMap;
import org.example.worktrack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Transactional
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMap userMap;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, UserMap userMap) {
        this.userRepository = userRepository;
        this.userMap = userMap;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }

    public UserDTO register (UserDTO userDTO){
        User user = userMap.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return userMap.toDto(savedUser);
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
        user.setEmail(email);
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
