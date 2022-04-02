package com.example.musify.service;

import com.example.musify.dto.UserDTO;
import com.example.musify.entities.User;
import com.example.musify.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class UserMapperImpl implements UserMapper{


    UserRepo userRepo;

    @Autowired
    public UserMapperImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDTO toDTO(User user) {
        return new UserDTO(user.getFirstName(),user.getLastName(),user.getEmail(),user.getPassword(),user.getCountryOfOrigin());
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        Integer id = userRepo.getId(userDTO);
        return new User(id,userDTO.getFirstName(),userDTO.getLastName(),userDTO.getEmail(),userDTO.getPassword(),userDTO.getCountryOfOrigin());
    }
}
