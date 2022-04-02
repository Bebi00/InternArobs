package com.example.musify.service;

import com.example.musify.dto.UserDTO;
import com.example.musify.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

}
