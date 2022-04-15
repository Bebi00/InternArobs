package com.example.musify.controllers;

import com.example.musify.dto.SongDTO;
import com.example.musify.dto.SongNewDTO;
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
        return new ResponseEntity<>(songService.getSongByTitle(title),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<SongDTO> saveSong(@RequestBody SongNewDTO songNewDTO){
        return new ResponseEntity<>(songService.saveSong(songNewDTO),HttpStatus.OK);
    }

    @PostMapping("/remove/{id}")
    public ResponseEntity<Optional<SongDTO>> removeSong(@PathVariable Long id){
        return new ResponseEntity<>(songService.removeById(id),HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<SongDTO> updateSong(@RequestBody SongNewDTO songNewDTO){
        return new ResponseEntity<>(songService.updateById(songNewDTO),HttpStatus.OK);
    }
}
