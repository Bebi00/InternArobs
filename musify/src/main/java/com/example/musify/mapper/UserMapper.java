package com.example.musify.mapper;

import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserNewDTO;
import com.example.musify.entities.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);
    List<UserDTO> toDTOs(List<User> users);

    User toEntity(UserDTO userDTO);

    User toNewEntity(UserNewDTO userNewDTO);
}
