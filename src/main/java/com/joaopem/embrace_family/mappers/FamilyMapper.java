package com.joaopem.embrace_family.mappers;

import com.joaopem.embrace_family.dto.FamilyPostRequestDTO;
import com.joaopem.embrace_family.dto.FamilyResponseDTO;
import com.joaopem.embrace_family.dto.FamilyUpdateRequestDTO;
import com.joaopem.embrace_family.model.Family;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FamilyMapper {

    Family toEntity(FamilyPostRequestDTO familyPostRequestDTO);

    Family toEntity(FamilyUpdateRequestDTO familyUpdateRequestDTO);

    FamilyResponseDTO toDTO(Family family);
}
