package com.example.musify.controllers;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.ArtistDTO;
import com.example.musify.security.JWTUtils;
import com.example.musify.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Optional<List<ArtistDTO>>> getArtists(){
        return new ResponseEntity<>(Optional.of(artistService.getAll()), HttpStatus.OK   );
    }

    @GetMapping("/{stageName}")
    public ResponseEntity<Optional<ArtistDTO>> getArtistByStageName(@PathVariable String stageName){
        return new ResponseEntity<>(Optional.of(artistService.getByStageName(stageName)),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<ArtistDTO>> getArtistByStageName(@PathVariable Long id){
        return new ResponseEntity<>(Optional.of(artistService.getById(id)),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<ArtistDTO> saveArtist(@RequestBody @Valid ArtistDTO artistDTO){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(artistService.saveArtist(artistDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ArtistDTO> removeArtist(@PathVariable Long id){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(artistService.removeById(id),HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ArtistDTO> updateArtist(@RequestBody @Valid ArtistDTO artistDTO){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(artistService.updateById(artistDTO),HttpStatus.OK);
    }

    @GetMapping("/albums")
    public ResponseEntity<Optional<List<AlbumDTO>>> getArtists(@RequestParam Long artistId){
        return new ResponseEntity<>(Optional.of(artistService.getAlbums(artistId)), HttpStatus.OK   );
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<List<ArtistDTO>>> searchArtists(@RequestParam String searchedString){
        return new ResponseEntity<>(Optional.of(artistService.searchArtistsByStageNameOrBand(searchedString)), HttpStatus.OK   );
    }
}
