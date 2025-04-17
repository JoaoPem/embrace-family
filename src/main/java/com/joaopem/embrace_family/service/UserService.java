package com.joaopem.embrace_family.service;

import com.joaopem.embrace_family.model.User;
import com.joaopem.embrace_family.repository.UserRepository;
import com.joaopem.embrace_family.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(User user){
        var password = user.getPassword();
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        userValidator.validateUser(user);
        userRepository.save(user);
    }

    public Optional<User> getById(UUID uuid){
        return userRepository.findById(uuid);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void deleteUser(User user){
        userRepository.delete(user);
    }

    public User getByEmail(String email){
        return userRepository.findByEmail(email);
    }


}
