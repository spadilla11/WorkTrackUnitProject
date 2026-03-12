package org.example.worktrack.mappers;

import org.example.worktrack.DTOs.UserDTO;
import org.example.worktrack.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ClientMap.class})
public interface UserMap {

    UserDTO toDto(User user);

    @Mapping(target = "password", ignore = true)
    User toEntity(UserDTO userDTO);

}
