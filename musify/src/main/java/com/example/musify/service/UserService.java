package com.example.musify.service;

import com.example.musify.dto.PlaylistDTO;
import com.example.musify.dto.UserDTO;
import com.example.musify.entities.Playlist;
import com.example.musify.entities.User;
import com.example.musify.exceptions.InvalidUserException;
import com.example.musify.exceptions.UnauthorizedException;
import com.example.musify.mapper.PlaylistMapper;
import com.example.musify.mapper.UserMapper;
import com.example.musify.repo.PlaylistRepo;
import com.example.musify.repo.UserRepo;
import com.example.musify.security.JWTUtils;
import com.example.musify.security.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PlaylistMapper playlistMapper;
    private final PlaylistRepo playlistRepo;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    public UserService(UserRepo userRepo, UserMapper userMapper, PlaylistMapper playlistMapper, PlaylistRepo playlistRepo) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.playlistMapper = playlistMapper;
        this.playlistRepo = playlistRepo;
    }

    @Transactional
    public UserDTO registerUser(UserDTO userDTO) {
        User user = userMapper.toNewEntity(userDTO);
        String encryptedPass = null;
        try {
            encryptedPass = PasswordEncryption.toHexString(PasswordEncryption.getSHA(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setPassword(encryptedPass);
        return userMapper.toDTO(userRepo.save(user));
    }

    @Transactional
    public String loginUser(String email, String password) {
        Optional<User> dbUser = userRepo.getByEmail(email);
        User user2 = null;
        if (dbUser.isPresent()) {
            user2 = dbUser.get();
        } else {
            try {
                throw new InvalidUserException("User was not found");
            } catch (InvalidUserException e) {
                e.printStackTrace();
            }
        }

        assert user2 != null;
        String encryptedPass = null;
        try {
            encryptedPass = PasswordEncryption.toHexString(PasswordEncryption.getSHA(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        assert encryptedPass != null;
        if (encryptedPass.equals(user2.getPassword()) && user2.getActive() == 1) {
            Object[] jwtInfo = jwtUtils.generateToken(user2.getId(), user2.getRole(), user2.getEmail());
            String token = jwtInfo[0].toString();
            Date expiryDate = (Date) jwtInfo[1];
            userRepo.addToken(token, user2.getId(), expiryDate);
            return token;
        } else {
            throw new InvalidUserException("User not found");
        }
    }

    @Transactional
    public Boolean logoutUser(String header) {
        String token = jwtUtils.getToken(header);
        userRepo.removeToken(token);
        return true;
    }

    @Transactional
    public UserDTO setAdmin(UserDTO userDTO) {
        List<Object> userInfo = (List<Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User newUser = null;
        if ((Integer) userInfo.get(1) == 1) {
            newUser = userRepo.setRole(userDTO, 1);
        } else {
                throw new UnauthorizedException("User does not have permission.");
        }
        return userMapper.toDTO(newUser);
    }

    public List<UserDTO> getAll() {
        // return userRepo.getAll();
        return null;
    }

    @Transactional
    public UserDTO get(int id) {
        Optional<User> user;
        user = userRepo.getById(id);
        if (user.isPresent()) {
            return userMapper.toDTO(user.get());
        } else {
            throw new InvalidUserException("User was not found");
        }
    }

    @Transactional
    public UserDTO getByEmail(String email) {
        Optional<User> user;
        user = userRepo.getByEmail(email);
        if (user.isPresent()) {
            return userMapper.toDTO(user.get());
        } else {
            throw new InvalidUserException("User was not found");
        }
    }

    @Transactional
    public UserDTO deleteUser(String header) {
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = (Integer) userInfo.get(0);
        return userMapper.toDTO(userRepo.inactivateUser(id));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        userRepo.update(userMapper.toEntity(userDTO));
        return userMapper.toDTO(userRepo.getById(userDTO.getId()).get());
    }

    @Transactional
    public List<PlaylistDTO> getPlaylists(){
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.getById((int)userInfo.get(0)).get();
        return playlistMapper.toDTOs(user.getPlaylists().stream().toList());
    }
}
