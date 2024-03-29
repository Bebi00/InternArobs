package com.example.musify.service;

import com.example.musify.dto.PlaylistDTO;
import com.example.musify.dto.UserDTO;
import com.example.musify.dto.UserNewDTO;
import com.example.musify.entities.User;
import com.example.musify.exceptions.InvalidMultipleException;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final PlaylistMapper playlistMapper;
    private final PlaylistRepo playlistRepo;
    private final RepoValidation repoValidation;
    private final JWTUtils jwtUtils;

    @Autowired
    public UserService(UserRepo userRepo, UserMapper userMapper, PlaylistMapper playlistMapper, PlaylistRepo playlistRepo, RepoValidation repoValidation, JWTUtils jwtUtils) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.playlistMapper = playlistMapper;
        this.playlistRepo = playlistRepo;
        this.repoValidation = repoValidation;
        this.jwtUtils = jwtUtils;
    }

    @Transactional
    public UserDTO registerUser(UserNewDTO userNewDTO) throws NoSuchAlgorithmException {
        validateUserCredentials(userNewDTO);

        String encryptedPass = PasswordEncryption.toHexString(PasswordEncryption.getSHA(userNewDTO.getPassword()));
        userNewDTO.setPassword(encryptedPass);
        User user = userMapper.toNewEntity(userNewDTO);
        userRepo.save(user);
        return userMapper.toDTO(userRepo.getByEmail(user.getEmail()).orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public String loginUser(String email, String password) throws NoSuchAlgorithmException {
        User dbUser = userRepo.getByEmail(email).orElseThrow(UserNotFoundException::new);
        String encryptedPass = PasswordEncryption.toHexString(PasswordEncryption.getSHA(password));

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
    public UserDTO setAdmin(Long userId) {
        repoValidation.checkUserById(userId);
        User modifiedUser = userRepo.setRole(userId, 1);
        return userMapper.toDTO(modifiedUser);
    }

    public List<UserDTO> getAll() {
        return userMapper.toDTOs(userRepo.getAll());
    }

    @Transactional
    public UserDTO getById(Long id) {
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
    public UserDTO updateUser(UserNewDTO userNewDTO) throws NoSuchAlgorithmException {
        validateUserCredentials(userNewDTO);
        String encryptedPass = PasswordEncryption.toHexString(PasswordEncryption.getSHA(userNewDTO.getPassword()));
        userNewDTO.setPassword(encryptedPass);
        userRepo.update(userMapper.toNewEntity(userNewDTO));
        return userMapper.toDTO(userRepo.getById(jwtUtils.getUserId()).orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public List<PlaylistDTO> getPlaylists() {
        return playlistMapper.toDTOs(userRepo.getPlaylists(jwtUtils.getUserId()));
    }

    public void validateUserCredentials(UserNewDTO userNewDTO){
        List<Exception> exceptions = new ArrayList<>();
        if(!(userNewDTO.getEmail().contains("@") && userNewDTO.getEmail().contains("."))){
            exceptions.add(new InvalidUserException("Email is not valid"));
        }
        if(userNewDTO.getPassword().contains(" ")){
            userNewDTO.setPassword(userNewDTO.getPassword().replaceAll(" ",""));
        }
        if(userRepo.getByEmail(userNewDTO.getEmail()).isPresent()){
            exceptions.add(new InvalidUserException("Email is already used"));
        }
        if(!exceptions.isEmpty()){
            throw new InvalidMultipleException(exceptions);
        }
    }
}
