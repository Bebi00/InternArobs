package com.example.musify.service;

import com.example.musify.dto.PlaylistDTO;
import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserNewDTO;
import com.example.musify.entities.User;
import com.example.musify.exceptions.InvalidUserException;
import com.example.musify.exceptions.UserNotFoundException;
import com.example.musify.mapper.PlaylistMapper;
import com.example.musify.mapper.UserMapper;
import com.example.musify.repo.PlaylistRepo;
import com.example.musify.repo.UserRepo;
import com.example.musify.security.JWTUtils;
import com.example.musify.security.PasswordEncryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

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
    public UserDTO registerUser(UserNewDTO userNewDTO) {
        User user = userMapper.toNewEntity(userNewDTO);
        String encryptedPass = null;
        try {
            encryptedPass = PasswordEncryption.toHexString(PasswordEncryption.getSHA(user.getPassword()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        user.setPassword(encryptedPass);
        userRepo.save(user);
        return userMapper.toDTO(userRepo.getByEmail(user.getEmail()).orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public String loginUser(String email, String password) {
        User dbUser = userRepo.getByEmail(email).orElseThrow(UserNotFoundException::new);

        String encryptedPass = null;
        try {
            encryptedPass = PasswordEncryption.toHexString(PasswordEncryption.getSHA(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert encryptedPass != null;

        if (encryptedPass.equals(dbUser.getPassword()) && dbUser.getActive() == 1) {
            Object[] jwtInfo = jwtUtils.generateToken(dbUser.getId(), dbUser.getRole(), dbUser.getEmail());
            String token = jwtInfo[0].toString();
            Date expiryDate = (Date) jwtInfo[1];
            userRepo.addToken(token, dbUser.getId(), expiryDate);
            return token;
        } else {
            throw new InvalidUserException("Invalid User credentials");
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
        User modifiedUser = userRepo.setRole(userDTO, 1);
        return userMapper.toDTO(modifiedUser);
    }

    public List<UserDTO> getAll() {
       return userMapper.toDTOs(userRepo.getAll());
    }

    @Transactional
    public UserDTO get(Long id) {
        return userMapper.toDTO(userRepo.getById(id).orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public UserDTO getByEmail(String email) {
        return userMapper.toDTO(userRepo.getByEmail(email).orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public UserDTO deleteUser() {
        return userMapper.toDTO(userRepo.inactivateUser(jwtUtils.getUserId()));
    }

    @Transactional
    public UserDTO updateUser(UserDTO userDTO) {
        userRepo.update(userMapper.toEntity(userDTO));
        return userMapper.toDTO(userRepo.getById(userDTO.getId()).orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public List<PlaylistDTO> getPlaylists(){
        return playlistMapper.toDTOs(userRepo.getPlaylists(jwtUtils.getUserId()));
    }
}
