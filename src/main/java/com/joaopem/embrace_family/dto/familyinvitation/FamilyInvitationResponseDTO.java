package com.joaopem.embrace_family.dto.familyinvitation;

import com.joaopem.embrace_family.dto.adoptiveparent.AdoptiveParentResponseDTO;
import com.joaopem.embrace_family.dto.family.FamilyResponseDTO;

public record FamilyInvitationResponseDTO(

        AdoptiveParentResponseDTO invitedAdoptiveParent,

        AdoptiveParentResponseDTO inviterAdoptiveParent,

        FamilyResponseDTO family

) {
}
