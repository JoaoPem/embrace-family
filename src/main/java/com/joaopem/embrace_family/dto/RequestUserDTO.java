package com.joaopem.embrace_family.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public record RequestUserDTO(

        @NotBlank(message = "required field")
        String fullName,

        @Past
        @NotNull(message = "Campo obrigatório")
        LocalDate birthDate,

        @Email(message = "Invalid Email")
        @NotBlank(message = "required field")
        String email,

        @Pattern(regexp = "^\\+55\\d{2}\\d{9}$", message = "Invalid Phone Number")
        @NotBlank(message = "required field")
        String phone,

        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]|:;\"'<>,.?/]).{8,}$",
                message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character"
        )
        @NotBlank(message = "required field")
        String password,

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
