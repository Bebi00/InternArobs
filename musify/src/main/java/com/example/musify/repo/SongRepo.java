package com.example.musify.repo;

import com.example.musify.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {

    @Override
    List<Song> findAll();

    Song findByTitle(String title);

    <Saved extends Song> Saved save(Saved song);

    void removeSongById(Long id);
}
