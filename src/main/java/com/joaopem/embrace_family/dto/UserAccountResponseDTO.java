package com.joaopem.embrace_family.dto;

import com.joaopem.embrace_family.enums.UserRole;

import java.util.UUID;

public record UserAccountResponseDTO(
        UUID id,
        String email,
        UserRole userRole
) {
}
