package com.t1tanic.budgetapp.service;

import com.t1tanic.budgetapp.model.User;

import java.util.Optional;

public interface UserService {
    User registerUser(User user);
    Optional<User> findByEmail(String email);
}
