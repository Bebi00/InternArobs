package com.example.musify.repo;

import com.example.musify.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepo extends JpaRepository<Artist,Long> {

    @Override
    List<Artist> findAll();

    Artist findByStageName(String stageName);

    Artist save(Artist artist);
}
