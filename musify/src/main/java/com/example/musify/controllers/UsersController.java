package com.example.musify.controllers;

import com.example.musify.dto.PlaylistDTO;
import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserNewDTO;
import com.example.musify.security.JWTUtils;
import com.example.musify.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> register(@RequestBody @Valid UserNewDTO userNewDTO){
        return new ResponseEntity<>(userService.registerUser(userNewDTO), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email,@RequestParam String password){
        String token = userService.loginUser(email, password);
        return new ResponseEntity<>(token, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Optional<UserDTO>> update(@RequestBody @Valid UserDTO userDTO){
        return new ResponseEntity<>(Optional.of(userService.updateUser(userDTO)), HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<Boolean> logout(@RequestHeader(name = "Authorization") String header) { // 'Authorization: Bearer tokenString'
        Boolean response = userService.logoutUser(header);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/setAdmin")
    public ResponseEntity<UserDTO> setAdmin(@RequestParam Long id){
        JWTUtils.checkUserRoleAdmin();
        UserDTO changedUser = userService.setAdmin(userService.get(id));
        return new ResponseEntity<>(changedUser,HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Optional<UserDTO>> delete() {
        return new ResponseEntity<>(Optional.of(userService.deleteUser()), HttpStatus.OK);
    }

    @GetMapping("/getPlaylists")
    public ResponseEntity<Optional<List<PlaylistDTO>>> getPlaylists(){
        return new ResponseEntity<>(Optional.of(userService.getPlaylists()),HttpStatus.OK);
    }
}
