package com.joaopem.embrace_family.mappers;

import com.joaopem.embrace_family.dto.family.FamilyPostRequestDTO;
import com.joaopem.embrace_family.dto.family.FamilyResponseDTO;
import com.joaopem.embrace_family.dto.family.FamilyUpdateRequestDTO;
import com.joaopem.embrace_family.model.Family;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FamilyMapper {

    Family toEntity(FamilyPostRequestDTO familyPostRequestDTO);

    Family toEntity(FamilyUpdateRequestDTO familyUpdateRequestDTO);

    FamilyResponseDTO toDTO(Family family);

    void updateFamilyFromDTO(FamilyUpdateRequestDTO familyUpdateRequestDTO, @MappingTarget Family family);
}
