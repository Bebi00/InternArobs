package com.example.musify.controllers;

import com.example.musify.dto.AlternativeTitleDTO;
import com.example.musify.dto.AlternativeTitleNewDTO;
import com.example.musify.dto.SongDTO;
import com.example.musify.dto.SongNewDTO;
import com.example.musify.exceptions.UnauthorizedException;
import com.example.musify.security.JWTUtils;
import com.example.musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/song")
public class SongController {
    private final SongService songService;

    @Autowired
    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SongDTO>> getSongs(){
        return new ResponseEntity<>(songService.getAll(), HttpStatus.OK );
    }

    @GetMapping("/{title}")
    public ResponseEntity<Optional<SongDTO>> getSongByTitle( @PathVariable String title){
        return new ResponseEntity<>(Optional.of(songService.getSongByTitle(title)),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<SongDTO>> getSongByTitle( @PathVariable Long id){
        return new ResponseEntity<>(Optional.of(songService.getById(id)),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<SongDTO> saveSong(@RequestBody @Valid SongNewDTO songNewDTO){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(songService.saveSong(songNewDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SongDTO> removeSong(@PathVariable Long id){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(songService.removeById(id),HttpStatus.OK);
    }

    @PutMapping("/{songId}")
    public ResponseEntity<SongDTO> updateSong(@PathVariable Long songId,@RequestBody @Valid SongNewDTO songNewDTO){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(songService.updateById(songId,songNewDTO),HttpStatus.OK);
    }

    @PostMapping("/AlternativeTitle/")
    public ResponseEntity<AlternativeTitleDTO> addAlternativeTitle(@RequestBody @Valid AlternativeTitleNewDTO alternativeTitleNewDTO){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(songService.saveAlternativeTitle(alternativeTitleNewDTO),HttpStatus.OK);
    }

    @DeleteMapping("/AlternativeTitle/{id}")
    public ResponseEntity<AlternativeTitleDTO> removeAlternativeTitle(@PathVariable Long id){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(songService.removeAlternativeTitleById(id),HttpStatus.OK);
    }

    @PutMapping("/AlternativeTitle/")
    public ResponseEntity<AlternativeTitleDTO> updateAlternativeTitle(@RequestBody @Valid AlternativeTitleNewDTO alternativeTitleNewDTO){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(songService.updateAlternativeTitle(alternativeTitleNewDTO),HttpStatus.OK);
    }

    @GetMapping("/AlternativeTitle/all/{songId}")
    public ResponseEntity<List<AlternativeTitleDTO>> getAllAlternativeTitles(@PathVariable Long songId){
        return new ResponseEntity<>(songService.getAllAlternativeTitles(songId),HttpStatus.OK);
    }

    @PostMapping("/addToPlaylist/")
    public ResponseEntity<SongDTO> addToPlaylist(@RequestParam Long songId,@RequestParam Long playlistId) throws UnauthorizedException {
        return new ResponseEntity<>(songService.addToPlaylist(songId, playlistId),HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<List<SongDTO>>> searchSongs(@RequestParam String searchedString){
        return new ResponseEntity<>(Optional.of(songService.searchSongs(searchedString)),HttpStatus.OK);
    }
}
