package com.joaopem.embrace_family.repository;

import com.joaopem.embrace_family.model.AdoptiveParent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdoptiveParentRepository extends JpaRepository <AdoptiveParent, UUID>{

    Optional<AdoptiveParent> findByPhone(String phone);

    boolean existsByIdAndUserAccountId(UUID adoptiveParentId, UUID userAccountId);
}
