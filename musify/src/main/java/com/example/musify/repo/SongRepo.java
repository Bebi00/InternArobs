package com.example.musify.repo;

import com.example.musify.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {
    Song findByTitle(String title);

    void removeSongById(Long id);

}
