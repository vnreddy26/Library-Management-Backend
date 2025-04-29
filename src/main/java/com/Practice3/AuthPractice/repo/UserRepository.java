package com.Practice3.AuthPractice.repo;

import com.Practice3.AuthPractice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String username);

    Boolean existsByUserName(String user);

    Boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
