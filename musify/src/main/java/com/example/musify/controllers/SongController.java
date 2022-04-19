package com.example.musify.controllers;

import com.example.musify.dto.AlternativeTitleDTO;
import com.example.musify.dto.AlternativeTitleNewDTO;
import com.example.musify.dto.SongDTO;
import com.example.musify.dto.SongNewDTO;
import com.example.musify.exceptions.UnauthorizedException;
import com.example.musify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/add")
    public ResponseEntity<SongDTO> saveSong(@RequestBody SongNewDTO songNewDTO){
        return new ResponseEntity<>(songService.saveSong(songNewDTO),HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<SongDTO> removeSong(@PathVariable Long id){
        return new ResponseEntity<>(songService.removeById(id),HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<SongDTO> updateSong(@RequestBody SongNewDTO songNewDTO){
        return new ResponseEntity<>(songService.updateById(songNewDTO),HttpStatus.OK);
    }

    @PostMapping("/AlternativeTitle/add")
    public ResponseEntity<AlternativeTitleDTO> addAlternativeTitle(@RequestBody AlternativeTitleNewDTO alternativeTitleNewDTO){
        return new ResponseEntity<>(songService.saveAlternativeTitle(alternativeTitleNewDTO),HttpStatus.OK);
    }

    @PostMapping("/AlternativeTitle/remove/{id}")
    public ResponseEntity<AlternativeTitleDTO> removeAlternativeTitle(@PathVariable Long id){
        return new ResponseEntity<>(songService.removeAlternativeTitleById(id),HttpStatus.OK);
    }

    @PostMapping("/AlternativeTitle/update")
    public ResponseEntity<AlternativeTitleDTO> updateAlternativeTitle(@RequestBody AlternativeTitleNewDTO alternativeTitleNewDTO){
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
}
