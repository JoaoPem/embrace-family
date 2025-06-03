package com.joaopem.embrace_family.dto.family;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FamilyUpdateRequestDTO(

        @NotBlank(message = "required field")
        @Size(max = 50)
        String familyName,

        @NotBlank
        @Size(max = 2000)
        String description,

        @NotBlank
        @Size(max = 1000)
        String familyCulture,

        @NotBlank
        @Size(max = 1000)
        String religiousTraditions,

        @NotBlank
        @Size(max = 1000)
        String sharedHobbies

//        @NotEmpty
//        List<@NotNull MultipartFile> familyPhotos
) {
}
