package com.example.musify.repo;

import com.example.musify.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepo extends JpaRepository<Artist, Long> {
    Artist findArtistById(Long id);
    Artist findByStageNameContaining(String stageName);
    List<Artist> findArtistsByStageNameContaining(String stageName);
    void removeArtistById(Long id);

    @Query(value = "SELECT DISTINCT a FROM Artist a LEFT JOIN a.bands b WHERE a.stageName LIKE %:searchedString% OR b.bandName LIKE %:searchedString%")
    List<Artist> searchArtistsByStageNameOrBand(@Param(value = "searchedString") String searchedString);
}
