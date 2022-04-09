package storage.controllers;

import com.example.musify.dto.UserDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.musify.service.UserService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserControllerTest {
    private final UserService userService;

    public UserControllerTest(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public void addUser(@RequestParam String firstName, String lastName){
        userService.addUser(firstName,lastName);
    }

    @GetMapping("/allUsers")
    public List<UserDTO> getAll(){
        return userService.getAll();
    }

//    @GetMapping("/getUser")
//    public Optional<UserDTO> getByID(@RequestParam Integer id){
//        return userService.get(id);
//    }
}
