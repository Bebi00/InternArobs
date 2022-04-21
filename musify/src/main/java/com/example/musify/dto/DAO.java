package com.example.musify.dto;

import com.example.musify.entities.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public interface DAO<T> {

    Optional<User> getById(Long id);

    Collection<T> getAll();

    T save(T t);

    void update(T t);

    void delete(T t);
}
