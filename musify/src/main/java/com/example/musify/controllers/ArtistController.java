package com.example.musify.controllers;

import com.example.musify.dto.ArtistDTO;
import com.example.musify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;


    @Autowired
    public ArtistController(ArtistService artistService) {
        this.artistService = artistService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ArtistDTO>> getArtists(){
        return new ResponseEntity<>(artistService.getAll(), HttpStatus.OK   );
    }

    @GetMapping("/{stageName}")
    public ResponseEntity<Optional<ArtistDTO>> getArtistByStageName(@PathVariable String stageName){
        return new ResponseEntity<>(artistService.getByStageName(stageName),HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Optional<ArtistDTO>> saveArtist(@RequestBody ArtistDTO artistDTO){
        return new ResponseEntity<>(artistService.addArtist(artistDTO),HttpStatus.OK);
    }
}
