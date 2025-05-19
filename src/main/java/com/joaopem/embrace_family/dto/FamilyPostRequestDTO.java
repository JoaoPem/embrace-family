package com.joaopem.embrace_family.dto;

import jakarta.validation.constraints.NotBlank;

public record FamilyPostRequestDTO(

        @NotBlank(message = "required field")
        String familyName

) {
}
