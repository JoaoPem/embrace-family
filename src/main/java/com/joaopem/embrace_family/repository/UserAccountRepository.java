package com.joaopem.embrace_family.repository;

import com.joaopem.embrace_family.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID> {

    @Query("SELECT u FROM UserAccount u WHERE u.email = :email")
    Optional<UserAccount> optionalFindByEmail(@Param("email") String email);

    UserAccount findByEmail(String email);

}
