package org.example.worktrack.service;

import org.example.worktrack.DTOs.UserDTO;
import org.example.worktrack.entities.User;
import org.example.worktrack.mappers.UserMap;
import org.example.worktrack.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMap userMap;

    public UserService(UserRepository userRepository, UserMap userMap){
        this.userRepository = userRepository;
        this.userMap = userMap;
    }

    public UserDTO register (UserDTO userDTO){
        User user = userMap.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return userMap.toDto(savedUser);
    }

    public boolean login(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()){
            User user = userOptional.get();
            return user.getPassword().equals(password);
        }
        return false;
    }
}
