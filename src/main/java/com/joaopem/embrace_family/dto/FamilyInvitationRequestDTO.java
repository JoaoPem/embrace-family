package com.joaopem.embrace_family.dto;

import java.util.UUID;

public record FamilyInvitationRequestDTO(
        UUID invitedAdoptiveParentId
) {
}
