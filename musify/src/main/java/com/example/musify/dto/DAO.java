package com.example.musify.dto;

import com.example.musify.entities.User;

import java.util.Collection;
import java.util.Optional;

public interface DAO<T> {

    Optional<User> getById(int id);

    Collection<T> getAll();

    T save(T t);

    void update(T t);

    void delete(T t);
}
