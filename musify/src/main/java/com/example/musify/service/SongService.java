package com.example.musify.service;

import com.example.musify.dto.SongDTO;
import com.example.musify.dto.SongNewDTO;
import com.example.musify.entities.Song;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.SongRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class SongService {
    private final SongRepo songRepo;
    private final SongMapper songMapper;


    @Autowired
    public SongService(SongRepo songRepo, SongMapper songMapper) {
        this.songRepo = songRepo;
        this.songMapper = songMapper;
    }


    public List<SongDTO> getAll() {
        return songMapper.toDTOs(songRepo.findAll());
    }

    public Optional<SongDTO> getSongByTitle(String title) {
        return Optional.of(songMapper.toDTO(songRepo.findByTitle(title)));
    }

    @Transactional
    public SongDTO saveSong(SongNewDTO songNewDTO) {
        Song newSong = songMapper.toEntityNew(songNewDTO);
        Song song = songRepo.save(newSong);
        return songMapper.toDTO(song);
    }

    @Transactional
    public Optional<SongDTO> removeById(Long id) {
        Song oldSong = songRepo.getById(id);
        songRepo.removeSongById(id);
        return Optional.of(songMapper.toDTO(oldSong));
    }

    @Transactional
    public SongDTO updateById(SongNewDTO songNewDTO){
       return saveSong(songNewDTO);
    }
}
