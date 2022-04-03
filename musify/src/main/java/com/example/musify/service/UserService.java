package com.example.musify.service;

import com.example.musify.dto.UserDTO;
import com.example.musify.entities.User;


import com.example.musify.exceptions.InvalidUserException;
import com.example.musify.exceptions.UnauthorizedException;
import com.example.musify.security.JWTUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.example.musify.repo.UserRepo;


import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
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
        User user = userMapper.toNewEntity(userDTO);
        UserDTO newUserDTO = userMapper.toDTO(userRepo.save(user));
        return newUserDTO;
    }

    public String loginUser(UserDTO userDTO) {
        User user1 = userMapper.toEntity(userDTO);
        Optional<User> dbUser = userRepo.getByEmail(user1.getEmail());
        User user2 = null;
        if (dbUser.isPresent()) {
            user2 = dbUser.get();
        }else {
            try {
                throw new InvalidUserException("User was not found");
            } catch (InvalidUserException e) {
                e.printStackTrace();
            }
        }


        assert user2 != null;
        if (user1.getEmail().equals(user2.getEmail()) && user1.getPassword().equals(user2.getPassword())) {
            Object[] jwtInfo = jwtUtils.generateToken(user2.getId(), user2.getRole(), user2.getEmail());
            String token = jwtInfo[0].toString();
            Date expiryDate = (Date) jwtInfo[1];
            userRepo.addToken(token, user2.getId(), expiryDate);
            return token;
        }

        return null;
    }

    public Boolean logoutUser(String header) {
        String token = jwtUtils.getToken(header);
        userRepo.removeToken(token);
        return true;
    }

    public UserDTO setAdmin(UserDTO userDTO) {
        List<Object> userInfo = (List<Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User newUser = null;
        if ((Integer) userInfo.get(1) == 1) {
           newUser = userRepo.setRole(userDTO,1);
        }else{
            try {
                throw new UnauthorizedException("User does not have permission.");
            } catch (UnauthorizedException e) {
                e.printStackTrace();
            }
        }
        return userMapper.toDTO(newUser);
    }

    public List<UserDTO> getAll() {
        // return userRepo.getAll();
        return null;
    }

    public UserDTO get(int id) {
        Optional<User> user;
        user = userRepo.getById(id);
        if (user.isPresent()) {
            return userMapper.toDTO(user.get());
        } else {
            try {
                throw new InvalidUserException("User was not found");
            } catch (InvalidUserException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public UserDTO getByEmail(String email) {
        Optional<User> user;
        user = userRepo.getByEmail(email);
        if (user.isPresent()) {
            return userMapper.toDTO(user.get());
        } else {
            try {
                throw new InvalidUserException("User was not found");
            } catch (InvalidUserException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
