package com.joaopem.embrace_family.dto.adoptiveparent;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record AdoptiveParentUpdateRequestDTO(

        @NotBlank(message = "required field")
        @Size(max = 100)
        String fullName,

        @Past
        @NotNull(message = "Campo obrigatório")
        LocalDate birthDate,

        @Pattern(regexp = "^\\+55\\d{2}\\d{9}$", message = "Invalid Phone Number")
        @NotBlank(message = "required field")
        String phone,

        @NotBlank(message = "required field")
        @Size(max = 30)
        String nationality,

        @NotBlank(message = "required field")
        @Size(max = 20)
        String maritalStatus,

        @NotBlank(message = "required field")
        @Size(max = 20)
        String genderIdentify,

        @NotBlank(message = "required field")
        @Size(max = 20)
        String sexualOrientation,

        @NotBlank(message = "required field")
        @Size(max = 30)
        String educationLevel,

        @NotBlank(message = "required field")
        @Size(max = 50)
        String occupation,

        @Positive
        @NotNull(message = "required field")
        BigDecimal monthlyIncome,

        @NotBlank(message = "required field")
        @Size(max = 1000)
        String hobbies,

        @NotBlank(message = "required field")
        @Size(max = 20)
        String religion
) {
}
