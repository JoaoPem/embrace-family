package com.joaopem.embrace_family.mappers;

import com.joaopem.embrace_family.dto.UserAccountPostRequestDTO;
import com.joaopem.embrace_family.dto.UserAccountResponseDTO;
import com.joaopem.embrace_family.model.UserAccount;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserAccountMapper {

    @Mapping(target = "id", ignore = true)
    UserAccount toEntity(UserAccountPostRequestDTO userAccountPostRequestDTORequestDTO);

    UserAccountResponseDTO toDTO(UserAccount userAccount);
}
