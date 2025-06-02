package com.joaopem.embrace_family.dto.useraccount;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record UserAccountUpdateRequestDTO(

        @Email(message = "Invalid Email")
        String email,

        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]|:;\"'<>,.?/]).{8,}$",
                message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character"
        )
        String password,

        String passwordConfirmation
) {
}
