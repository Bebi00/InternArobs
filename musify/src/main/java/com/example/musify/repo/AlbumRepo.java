package com.example.musify.repo;

import com.example.musify.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Long> {
    Album findAlbumByTitle(String title);
    Album findAlbumById(Long id);
    void removeAlbumById(Long id);

    @Query(value = "SELECT DISTINCT a FROM Album a LEFT JOIN a.songs s WHERE a.title LIKE %:searchedString% or s.title LIKE %:searchedString%")
    List<Album> searchAlbumByTitleOrSong(@Param(value = "searchedString") String searchedString);
}
