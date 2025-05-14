package com.joaopem.embrace_family.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record AdoptiveParentUpdateRequestDTO(

        @NotBlank(message = "required field")
        String fullName,

        @Past
        @NotNull(message = "Campo obrigatório")
        LocalDate birthDate,

        @Pattern(regexp = "^\\+55\\d{2}\\d{9}$", message = "Invalid Phone Number")
        @NotBlank(message = "required field")
        String phone,

        @NotBlank(message = "required field")
        String nationality,

        @NotBlank(message = "required field")
        String maritalStatus,

        @NotBlank(message = "required field")
        String genderIdentify,

        @NotBlank(message = "required field")
        String sexualOrientation,

        @NotBlank(message = "required field")
        String educationLevel,

        @NotBlank(message = "required field")
        String occupation,

        @Positive
        @NotNull(message = "required field")
        BigDecimal monthlyIncome,

        @NotBlank(message = "required field")
        String hobbies,

        @NotBlank(message = "required field")
        String religion
) {
}
