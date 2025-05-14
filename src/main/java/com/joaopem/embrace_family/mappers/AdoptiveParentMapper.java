package com.joaopem.embrace_family.mappers;

import com.joaopem.embrace_family.dto.AdoptiveParentResponseDTO;
import com.joaopem.embrace_family.dto.AdoptiveParentUpdateRequestDTO;
import com.joaopem.embrace_family.model.AdoptiveParent;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdoptiveParentMapper {

    AdoptiveParent toEntity(AdoptiveParentUpdateRequestDTO adoptiveParentRequestDTO);

    AdoptiveParentResponseDTO toDTO(AdoptiveParent adoptiveParent);

    void updateFromDTO(@MappingTarget AdoptiveParent adoptiveParent, AdoptiveParentUpdateRequestDTO adoptiveParentUpdateRequestDTO);
}
