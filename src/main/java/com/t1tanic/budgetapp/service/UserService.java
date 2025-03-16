package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.model.User;

import java.util.Optional;

/**
 * Service for managing user accounts.
 */
public interface UserService {

    /**
     * Registers a new user.
     * @param user The user to register
     * @return The registered user
     */
    User registerUser(User user);

    /**
     * Retrieves a user by their email address.
     * @param email The email address to search for
     * @return The user with the specified email address
     */
    Optional<User> findByEmail(String email);
}
