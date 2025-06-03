package com.joaopem.embrace_family.dto.family;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FamilyPostRequestDTO(

        @NotBlank(message = "required field")
        @Size(max = 50)
        String familyName

) {
}
