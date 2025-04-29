package com.Practice3.AuthPractice.interfaces;

import com.Practice3.AuthPractice.model.User;
import com.Practice3.AuthPractice.payload.UserDTO;

import java.util.List;
import java.util.Optional;

public interface CustomUserServiceInterface {
    void updateUserRole(Long userId, String roleName);

    List<User> getAllUsers();

    UserDTO getUserById(Long id);

    User findByUsername(String username);

    Optional<User> findByEmail(String email);

    User registerUser(User user);
}
