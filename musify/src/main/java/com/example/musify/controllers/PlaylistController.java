package com.example.musify.controllers;


import com.example.musify.dto.PlaylistDTO;
import com.example.musify.dto.PlaylistNewDTO;
import com.example.musify.service.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/playlist")
public class PlaylistController {
    private final PlaylistService playlistService;

    @Autowired
    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<PlaylistDTO>> getPlaylistById(@PathVariable Long id){
        return new ResponseEntity<>(Optional.of(playlistService.getPlaylistById(id)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<PlaylistDTO> addPlaylist(@RequestBody PlaylistNewDTO playlistNewDTO){
        return new ResponseEntity<>(playlistService.savePlaylist(playlistNewDTO),HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<PlaylistDTO> updatePlaylist(@RequestBody PlaylistNewDTO playlistNewDTO){
        return new ResponseEntity<>(playlistService.updatePlaylist(playlistNewDTO),HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<PlaylistDTO> removePlaylistById(@PathVariable Long id){
        return new ResponseEntity<>(playlistService.removePlaylistById(id), HttpStatus.OK);
    }
}
