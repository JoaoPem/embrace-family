package com.joaopem.embrace_family.mappers;

import com.joaopem.embrace_family.dto.RequestUserDTO;
import com.joaopem.embrace_family.dto.ResponseUserDTO;
import com.joaopem.embrace_family.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RequestUserDTO requestUserDTO);

    ResponseUserDTO toDTO(User user);
}
