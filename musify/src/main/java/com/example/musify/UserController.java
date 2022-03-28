package com.example.musify;

import com.example.musify.repo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.musify.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public void addUser(@RequestParam String firstName, String lastName){
        userService.addUser(firstName,lastName);
    }

    @GetMapping("/allUsers")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/getUser")
    public Optional<User> getByID(@RequestParam Integer id){
        return userService.get(id);
    }
}
