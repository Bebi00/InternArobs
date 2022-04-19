package com.example.musify.mapper;

import com.example.musify.dto.UserDTO;
import com.example.musify.entities.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserMapperImplTest {

    @BeforeAll
    public static void setup() {
        System.out.println("Setup was executed.");
    }

    @BeforeEach
    public void init() {
        System.out.println("Init Executed");
    }

    @Test
    @DisplayName("Test if the user entity is mapped correctly to the UserDTO")
    public void givenValidUser_whenMappingToUserDTO_thenReturnCorrectValidDTO() {
        Integer id = 24;
        String firstName = "Paul";
        String lastName = "Bratian";
        String email = "pb@arobs.com";
        String password = "pass";
        String countryOfOrigin = "RO";
        Integer role = 0;
        Integer active = 1;
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        user.setCountryOfOrigin(countryOfOrigin);
        user.setRole(role);
        user.setActive(active);

        UserMapper userMapper = new UserMapperImpl();
        UserDTO userDTO = userMapper.toDTO(user);

        assertEquals(userDTO.getPassword(), password);
    }
}