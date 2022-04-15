package com.example.musify.repo;

import com.example.musify.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {
    Artist findArtistById(Long id);
    Artist findByStageName(String stageName);
    void removeArtistById(Long id);
}
