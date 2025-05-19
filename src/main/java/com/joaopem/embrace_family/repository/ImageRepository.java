package com.joaopem.embrace_family.repository;

import com.joaopem.embrace_family.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {
}
