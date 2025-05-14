package com.joaopem.embrace_family.dto;

import com.joaopem.embrace_family.model.UserRole;
import jakarta.validation.constraints.*;

public record UserAccountPostRequestDTO(

        @Email(message = "Invalid Email")
        @NotBlank(message = "required field")
        String email,

        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-={}\\[\\]|:;\"'<>,.?/]).{8,}$",
                message = "Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, one number, and one special character"
        )
        @NotBlank(message = "required field")
        String password,

        @NotBlank(message = "required field")
        String passwordConfirmation

) {

        @AssertTrue(message = "password must match")
        private boolean isPasswordConfirmed(){
                return password != null && password.equals(passwordConfirmation);
        }
}
