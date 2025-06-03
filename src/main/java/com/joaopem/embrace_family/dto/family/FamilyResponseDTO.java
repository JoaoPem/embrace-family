package com.joaopem.embrace_family.dto.family;

import com.joaopem.embrace_family.dto.adoptiveparent.AdoptiveParentResponseDTO;
import com.joaopem.embrace_family.model.AdoptiveParent;

public record FamilyResponseDTO(
        String familyName,

        AdoptiveParentResponseDTO adoptiveParent1,

        AdoptiveParentResponseDTO adoptiveParent2,

        String description,

        String familyCulture,

        String religiousTraditions,

        String sharedHobbies
) {
}
