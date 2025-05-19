package com.joaopem.embrace_family.repository;

import com.joaopem.embrace_family.model.Family;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FamilyRepository extends JpaRepository<Family, UUID> {
}
