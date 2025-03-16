package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.exception.UserAlreadyExistsException;
import com.t1tanic.budgetapp.exception.UserNotFoundException;
import com.t1tanic.budgetapp.model.User;
import com.t1tanic.budgetapp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registerUser(User user) {
        log.info("Registering User");
        // Check if the email is already registered
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User with email " + user.getEmail() + " already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword())); // Secure password storage
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        log.info("Finding User with email {}", email);
        return userRepository.findByEmail(email)
                .or(() -> {
                    throw new UserNotFoundException("User with email " + email + " not found.");
                });
    }
}
