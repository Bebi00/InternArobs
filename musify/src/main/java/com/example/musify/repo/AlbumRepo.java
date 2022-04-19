package com.example.musify.repo;

import com.example.musify.entities.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlbumRepo extends JpaRepository<Album, Long> {
    Album findAlbumByTitle(String title);
    Album findAlbumById(Long id);
    void removeAlbumById(Long id);
}
