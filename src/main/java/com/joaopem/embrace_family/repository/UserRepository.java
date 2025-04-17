package com.joaopem.embrace_family.repository;

import com.joaopem.embrace_family.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID>{

    Optional<User> findByEmailOrPhone(String email, String phone);

    User findByEmail(String email);
}
