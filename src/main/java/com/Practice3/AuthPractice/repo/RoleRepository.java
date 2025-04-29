package com.Practice3.AuthPractice.repo;

import com.Practice3.AuthPractice.model.AppRole;
import com.Practice3.AuthPractice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(AppRole roleName);  // Custom query method
}
