package com.example.musify.controllers;

import com.example.musify.dto.AlbumDTO;
import com.example.musify.dto.BandDTO;
import com.example.musify.dto.BandNewDTO;
import com.example.musify.security.JWTUtils;
import com.example.musify.service.BandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/band")
public class BandController {
    private final BandService bandService;

    @Autowired
    public BandController(BandService bandService) {
        this.bandService = bandService;
    }

    @GetMapping("/all")
    public ResponseEntity<Optional<List<BandDTO>>> getBands(){
        return new ResponseEntity<>(Optional.of(bandService.getAll()), HttpStatus.OK );
    }

    @GetMapping("/{bandName}")
    public ResponseEntity<Optional<BandDTO>> getBandByBandName(@PathVariable String bandName){
        return new ResponseEntity<>(Optional.of(bandService.getByBandName(bandName)),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<BandDTO> saveBand(@RequestBody @Valid BandNewDTO bandDTO){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(bandService.saveBand(bandDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BandDTO> removeBand(@PathVariable Long id){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(bandService.removeById(id),HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<BandDTO> updateBand(@RequestBody @Valid BandNewDTO bandDTO){
        JWTUtils.checkUserRoleAdmin();
        return new ResponseEntity<>(bandService.updateById(bandDTO),HttpStatus.OK);
    }

    @GetMapping("/albums")
    public ResponseEntity<Optional<List<AlbumDTO>>> getArtists(@RequestParam Long bandId){
        return new ResponseEntity<>(Optional.of(bandService.getAlbums(bandId)), HttpStatus.OK   );
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<List<BandDTO>>> searchArtists(@RequestParam String searchedString){
        return new ResponseEntity<>(Optional.of(bandService.searchBandByBandNameOrArtist(searchedString)), HttpStatus.OK   );
    }
}
