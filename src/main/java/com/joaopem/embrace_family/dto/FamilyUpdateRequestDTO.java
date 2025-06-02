package com.joaopem.embrace_family.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record FamilyUpdateRequestDTO(

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
