package com.example.musify.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserDTOTest {

    @Test
    public void givenValidUserDTO_whenSerializingAndDeserializing_thenResultIsTheSame() throws JsonProcessingException {
        UserDTO userDTO = new UserDTO(1, "Paul", "Bratian", "pb@arobs.com", "pass", "RO", 0, 1);

        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString = objectMapper.writeValueAsString(userDTO);
        UserDTO userDTO1 = objectMapper.readValue(valueAsString, UserDTO.class);

        assertEquals(userDTO,userDTO1);
    }
}