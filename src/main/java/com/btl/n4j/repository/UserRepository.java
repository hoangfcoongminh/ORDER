package com.btl.n4j.repository;

import com.btl.n4j.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findOptionalByUsername(String username);
    User findByUsername(String username);
}
