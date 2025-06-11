package com.joaopem.embrace_family.dto.familyinvitation;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record FamilyInvitationRequestDTO(
        @NotNull(message = "Invited adoptive parent ID is required")
        UUID invitedAdoptiveParentId
) {
}
