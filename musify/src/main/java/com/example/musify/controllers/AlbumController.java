package com.example.musify.controllers;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.AlbumNewDTO;
import com.example.musify.exceptions.InvalidArtistException;
import com.example.musify.exceptions.UnauthorizedException;
import com.example.musify.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/album")
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<AlbumDTO>> getAlbumById(@PathVariable Long id){
        return new ResponseEntity<>(Optional.of(albumService.getAlbumById(id)), HttpStatus.OK);
    }

    @GetMapping("/{title}")
    public ResponseEntity<Optional<AlbumDTO>> getAlbumById(@PathVariable String title){
        return new ResponseEntity<>(Optional.of(albumService.getAlbumByTitle(title)), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AlbumDTO> addAlbum(@RequestBody AlbumNewDTO albumNewDTO) throws InvalidArtistException {
        return new ResponseEntity<>(albumService.saveAlbum(albumNewDTO),HttpStatus.OK);
    }

    @PostMapping("/removeById/{id}")
    public ResponseEntity<AlbumDTO> removeAlbumById(@PathVariable Long id){
        return new ResponseEntity<>(albumService.removeAlbumById(id),HttpStatus.OK);
    }

    @PostMapping("/updateById")
    public ResponseEntity<AlbumDTO> updateAlbumById(@RequestBody AlbumNewDTO albumNewDTO) throws InvalidArtistException {
        return new ResponseEntity<>(albumService.updateAlbumById(albumNewDTO),HttpStatus.OK);
    }

    @PostMapping("/addToPlaylist/")
    public ResponseEntity<AlbumDTO> addToPlaylist(@RequestParam Long albumId, @RequestParam Long playlistId) throws UnauthorizedException {
        return new ResponseEntity<>(albumService.addToPlaylist(albumId, playlistId),HttpStatus.OK);
    }

}
