package com.example.musify.service;

import com.example.musify.dto.SongDTO;
import com.example.musify.entities.Playlist;
import com.example.musify.entities.Song;
import com.example.musify.entities.User;
import com.example.musify.exceptions.PlaylistNotFoundException;
import com.example.musify.mapper.PlaylistMapper;
import com.example.musify.mapper.SongMapper;
import com.example.musify.mapper.SongMapperImpl;
import com.example.musify.repo.PlaylistRepo;
import com.example.musify.repo.SongRepo;
import com.example.musify.repo.UserRepo;
import com.example.musify.security.JWTUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaylistServiceTest {
    @Mock
    PlaylistRepo playlistRepo;
    @Mock
    PlaylistMapper playlistMapper;
    @Mock
    UserRepo userRepo;
    @Mock
    SongRepo songRepo;

    SongMapper songMapper;
    @Mock
    JWTUtils jwtUtils;
    @Mock
    RepoValidation repoValidation;

    PlaylistService playlistService;

    @BeforeEach
    public void init() {
        songMapper = new SongMapperImpl();
        playlistService = new PlaylistService(playlistRepo, playlistMapper, userRepo, songRepo, songMapper, jwtUtils, repoValidation);
    }

    @Test
    public void givenNotExistingPlaylist_whenChangingSongOrder_thenThrowPlaylistNotFoundException(){
       when(playlistRepo.findPlaylistById(any())).thenReturn(null);

        assertThrows(PlaylistNotFoundException.class,() -> playlistService.changeSongOrder(10L,20L,2,4));
    }

    @Test
    public void givenExistingUser_whenChangingSongOrder_thenReturnValidResponse(){
        Playlist playlist =  new Playlist();
        playlist.setOwnerUser(24L);
        Song song1 = new Song();
        song1.setDuration(10);
        song1.setId(30L);
        Song song2 = new Song();
        song2.setDuration(10);
        song2.setId(40L);
        LinkedList<Song> songs = new LinkedList<>();
        songs.add(song1);
        songs.add(song2);
        playlist.setSongs(songs);
        when(playlistRepo.findPlaylistById(any())).thenReturn(playlist);
        when(songRepo.findSongById(any())).thenReturn(song2);

        User user = new User();
        user.setId(24L);
        when(jwtUtils.getUserId()).thenReturn(24L);
        when(userRepo.getById(24L)).thenReturn(Optional.of(user));
        when(playlistRepo.save(any())).thenReturn(playlist);

        List<SongDTO> songDTOS= playlistService.changeSongOrder(10L,40L,2,1);
        assertEquals(songDTOS.get(0).getId(),song2.getId());
        assertEquals(songDTOS.get(1).getId(),song1.getId());
    }


}