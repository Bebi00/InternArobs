package com.example.musify.repo;

import com.example.musify.entities.AlternativeTitle;
import com.example.musify.entities.Artist;
import com.example.musify.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepo extends JpaRepository<Song, Long> {
    Song findByTitle(String title);

    void removeSongById(Long id);

    Song findSongById(Long id);

    @Query(value = "SELECT DISTINCT s FROM Song s LEFT JOIN s.alternativeTitles at LEFT JOIN s.album a " +
            "WHERE s.title LIKE %:searchedString% OR at.alternativeTitle LIKE %:searchedString%  OR a.title LIKE %:searchedString%")
    List<Song> searchSongs(@Param(value = "searchedString") String searchedString);

    List<Song> findSongByTitleContaining(String title);
    List<Song> findSongByAlternativeTitlesContains(AlternativeTitle alternativeTitle);
    List<Song> findSongsByArtistsContains(Artist artist);
}
