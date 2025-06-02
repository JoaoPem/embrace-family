package com.joaopem.embrace_family.mappers;

import com.joaopem.embrace_family.dto.FamilyInvitationRequestDTO;
import com.joaopem.embrace_family.model.FamilyInvitation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FamilyInvitationMapper {

    FamilyInvitation toEntity(FamilyInvitationRequestDTO familyInvitationRequestDTO);

}
