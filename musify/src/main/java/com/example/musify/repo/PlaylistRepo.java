package com.example.musify.repo;

import com.example.musify.entities.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepo extends JpaRepository<Playlist,Long> {

    Playlist findPlaylistById(Long id);
    void removePlaylistById(Long id);
}
