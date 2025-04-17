package com.joaopem.embrace_family.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ResponseUserDTO(

        String fullName,

        LocalDate birthDate,

        String email,

        String phone,

        String nationality,

        String maritalStatus,

        String genderIdentify,

        String sexualOrientation,

        String educationLevel,

        String occupation,

        BigDecimal monthlyIncome,

        String hobbies,

        String religion

) {
}
