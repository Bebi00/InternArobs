package com.example.musify.service;

import com.example.musify.repo.User;
import org.springframework.stereotype.Service;
import com.example.musify.repo.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserService{
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void addUser(String firstName, String lastName) {
        User user=new User(firstName,lastName);
        userRepo.save(user);
    }

    public List<User> getAll(){
        return userRepo.getAll();
    }

    public Optional<User> get(int id){
        return userRepo.get(id);
    }




}
