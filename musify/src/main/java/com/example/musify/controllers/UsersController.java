package com.example.musify.controllers;

import com.example.musify.dto.UserDTO;
import com.example.musify.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

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
    public ResponseEntity<String> login(@RequestParam String email,@RequestParam String password){
        String token = userService.loginUser(email, password);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Optional<UserDTO>> update(@RequestBody @Valid UserDTO userDTO){
        return new ResponseEntity<>(userService.updateUser(userDTO), HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader(name = "Authorization") String header) { // 'Authorization: Bearer tokenString'
        Boolean response = userService.logoutUser(header);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/setAdmin")
    public ResponseEntity<UserDTO> setAdmin(@RequestParam int id){
        UserDTO changedUser = userService.setAdmin(userService.get(id));
        return new ResponseEntity<>(changedUser,HttpStatus.OK);
    }

    @PostMapping("/delete")
    public ResponseEntity<Optional<UserDTO>> delete(@RequestHeader(name = "Authorization") String header) {
        return new ResponseEntity<>(userService.deleteUser(header), HttpStatus.OK);
    }
}
