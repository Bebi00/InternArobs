package com.example.musify.controllers;

import com.example.musify.dto.UserDTO;
import com.example.musify.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserDTO userDTO){
        UserDTO user = userService.registerUser(userDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid UserDTO userDTO){
        String token = userService.loginUser(userDTO);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(){
        boolean completed = userService.logoutUser();
        return new ResponseEntity<>(completed, HttpStatus.OK);
    }
}
