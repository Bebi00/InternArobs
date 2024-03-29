package com.example.musify.controllers;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.AlbumNewDTO;
import com.example.musify.dto.SongDTO;
import com.example.musify.exceptions.InvalidArtistException;
import com.example.musify.exceptions.UnauthorizedException;
import com.example.musify.security.JWTUtils;
import com.example.musify.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
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

    @PostMapping("/")
    public ResponseEntity<AlbumDTO> addAlbum(@RequestBody @Valid AlbumNewDTO albumNewDTO) throws InvalidArtistException {
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(albumService.saveAlbum(albumNewDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AlbumDTO> removeAlbumById(@PathVariable Long id){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(albumService.removeAlbumById(id),HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<AlbumDTO> updateAlbumById(@RequestBody @Valid AlbumNewDTO albumNewDTO) throws InvalidArtistException {
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(albumService.updateAlbumById(albumNewDTO),HttpStatus.OK);
    }

    @PostMapping("/ToPlaylist/")
    public ResponseEntity<AlbumDTO> addToPlaylist(@RequestParam Long albumId, @RequestParam Long playlistId) throws UnauthorizedException {
        return new ResponseEntity<>(albumService.addToPlaylist(albumId, playlistId),HttpStatus.OK);
    }

    @GetMapping("/songs")
    public ResponseEntity<Optional<List<SongDTO>>> getSongsFromAlbum(@RequestParam Long albumId){
        return new ResponseEntity<>(Optional.of(albumService.getSongsFromAlbum(albumId)), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<List<AlbumDTO>>> searchAlbum(@RequestParam String searchedString){
        return new ResponseEntity<>(Optional.of(albumService.searchAlbumsByTitleOrSongs(searchedString)), HttpStatus.OK);
    }

}
