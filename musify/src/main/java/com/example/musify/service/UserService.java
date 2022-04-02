package com.example.musify.service;

import com.example.musify.dto.UserDTO;
import com.example.musify.entities.User;
import com.example.musify.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.musify.repo.UserRepo;

import javax.swing.text.StyledEditorKit;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
        this.userMapper = new UserMapperImpl(userRepo);
    }

    public void addUser(String firstName, String lastName) {
        //UserDTO user=new UserDTO(firstName,lastName);
        // userRepo.save(user);
    }

    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        userRepo.save(user);
        return userDTO;
    }

    public String loginUser(UserDTO userDTO) {

        User user1 = userMapper.toEntity(userDTO);
        User user2 = userRepo.getByEmail(userDTO.getEmail()).get();

        if (user1.getEmail().equals(user2.getEmail()) && user1.getPassword().equals(user2.getPassword())) {
            return jwtUtils.generateToken(user1.getId(), user1.getRole(), user1.getEmail());
        }

        return null;
    }

    public Boolean logoutUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<Object> userInfo = (List<Object>) authentication.getPrincipal();
        System.out.println(userInfo);
        return true;
    }

    public UserDTO setAdmin(UserDTO userDTO) {
        return null;
    }

    public List<UserDTO> getAll() {
        // return userRepo.getAll();
        return null;
    }

    public Optional<UserDTO> get(int id) {
        // return userRepo.get(id);
        return null;
    }


}
