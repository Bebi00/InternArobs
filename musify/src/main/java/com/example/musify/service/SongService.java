package com.example.musify.service;

import com.example.musify.dto.AlternativeTitleDTO;
import com.example.musify.dto.AlternativeTitleNewDTO;
import com.example.musify.dto.SongDTO;
import com.example.musify.dto.SongNewDTO;
import com.example.musify.entities.AlternativeTitle;
import com.example.musify.entities.Playlist;
import com.example.musify.entities.Song;
import com.example.musify.exceptions.InvalidSongException;
import com.example.musify.exceptions.SongNotFoundException;
import com.example.musify.exceptions.UnauthorizedException;
import com.example.musify.mapper.AlternativeTitleMapper;
import com.example.musify.mapper.SongMapper;
import com.example.musify.repo.AlternativeTitleRepo;
import com.example.musify.repo.ArtistRepo;
import com.example.musify.repo.PlaylistRepo;
import com.example.musify.repo.SongRepo;
import com.example.musify.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class SongService {
    private final SongRepo songRepo;
    private final SongMapper songMapper;
    private final ArtistRepo artistRepo;
    private final AlternativeTitleRepo alternativeTitleRepo;
    private final AlternativeTitleMapper alternativeTitleMapper;
    private final PlaylistRepo playlistRepo;
    private final JdbcTemplate jdbcTemplate;
    private final JWTUtils jwtUtils;


    @Autowired
    public SongService(SongRepo songRepo, SongMapper songMapper, ArtistRepo artistRepo, AlternativeTitleRepo alternativeTitleRepo, AlternativeTitleMapper alternativeTitleMapper, PlaylistRepo playlistRepo, JdbcTemplate jdbcTemplate, JWTUtils jwtUtils) {
        this.songRepo = songRepo;
        this.songMapper = songMapper;
        this.artistRepo = artistRepo;
        this.playlistRepo = playlistRepo;
        this.alternativeTitleRepo = alternativeTitleRepo;
        this.alternativeTitleMapper = alternativeTitleMapper;
        this.jdbcTemplate = jdbcTemplate;
        this.jwtUtils = jwtUtils;
    }


    public List<SongDTO> getAll() {
        return songMapper.toDTOs(songRepo.findAll());
    }

    public SongDTO getById(Long id) {
        return songMapper.toDTO(songRepo.findSongById(id));
    }

    public SongDTO getSongByTitle(String title) {
        return songMapper.toDTO(songRepo.findByTitle(title));
    }

//    @Transactional
//    public SongDTO saveSong(SongNewDTO songNewDTO) {
//        Song newSong = songMapper.toEntityNew(songNewDTO);
//        Song song = songRepo.save(newSong);
//        return songMapper.toDTO(song);
//    }

    @Transactional
    public SongDTO saveSong(SongNewDTO songNewDTO) {
        Song newSong = songMapper.toEntityNew(songNewDTO);
        songNewDTO.getArtistIds()
                .stream()
                .map(artistRepo::findArtistById)
                .forEach(newSong::addArtist);
        Song song = songRepo.save(newSong);
        return songMapper.toDTO(song);
    }


    @Transactional
    public SongDTO removeById(Long id) {
        Song oldSong = songRepo.getById(id);
        songRepo.removeSongById(id);
        return songMapper.toDTO(oldSong);
    }

    @Transactional
    public SongDTO updateById(SongNewDTO songNewDTO) {
        if (getById(songNewDTO.getId()) != null) {
            return saveSong(songNewDTO);
        }
        throw new InvalidSongException();
    }

    public AlternativeTitleDTO getAlternativeTitleById(Long id) {
        return alternativeTitleMapper.toDTO(alternativeTitleRepo.getById(id));
    }

    public List<AlternativeTitleDTO> getAllAlternativeTitles(Long songId) {
        Song song = songRepo.findSongById(songId);
        if (song != null) {
            return alternativeTitleMapper.toDTOs(song.getAlternativeTitles());
        }
        throw new InvalidSongException();
    }

    @Transactional
    public AlternativeTitleDTO saveAlternativeTitle(AlternativeTitleNewDTO alternativeTitleNewDTO) {
        Song song = songRepo.findSongById(alternativeTitleNewDTO.getSongId());
        song.addAlternativeTitle(alternativeTitleMapper.toEntity(alternativeTitleNewDTO));
        songRepo.save(song);
        return alternativeTitleMapper.toDTO(alternativeTitleRepo.findByAlternativeTitleContaining(alternativeTitleNewDTO.getAlternativeTitle()));
    }

    @Transactional
    public AlternativeTitleDTO updateAlternativeTitle(AlternativeTitleNewDTO alternativeTitleNewDTO) {
        return saveAlternativeTitle(alternativeTitleNewDTO);
    }


    @Transactional
    public AlternativeTitleDTO removeAlternativeTitleById(Long id) {
        AlternativeTitle alternativeTitle = alternativeTitleRepo.getById(id);
        alternativeTitle.getSong().removeAlternativeTitle(alternativeTitle);
        return alternativeTitleMapper.toDTO(alternativeTitle);
    }

    @Transactional
    public SongDTO addToPlaylist(Long songId,Long playlistId) throws UnauthorizedException {
        List<?> userInfo = (List<?>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Song song = songRepo.findSongById(songId);
        Playlist playlist = playlistRepo.findPlaylistById(playlistId);
        if(!Objects.equals(playlist.getOwnerUser(), jwtUtils.getUserId())){
            throw new UnauthorizedException("Only the owner of the playlist can modify its content");
        }
        if(song == null){
            throw new SongNotFoundException("Song with the given id was not found");
        }
        if(playlist == null){
            throw new SongNotFoundException("Playlist with the given id was not found");
        }
        LocalDateTime localDateTime = LocalDateTime.now();
        playlist.setLastUpdatedDate(localDateTime);
        playlist.addSong(song);
        songRepo.save(song);
        return songMapper.toDTO(songRepo.save(song));
    }

    @Transactional
    public List<SongDTO> searchSongsByTitleOrAlternativeTitleOrArtist(String searchedString){
        Set<Song> songs = new HashSet<>();
        songs.addAll(songRepo.findSongByTitleContaining(searchedString));
        alternativeTitleRepo.findAlternativeTitleByAlternativeTitleContaining(searchedString)
                .forEach(alternativeTitle ->  songs.addAll(songRepo.findSongByAlternativeTitlesContains(alternativeTitle)));
        artistRepo.findArtistsByStageNameContaining(searchedString).forEach(artist -> songs.addAll(songRepo.findSongsByArtistsContains(artist)));
        return songMapper.toDTOs(songs.stream().toList());
    }

    @Transactional
    public List<SongDTO> searchSongs(String searchedString){
        return songMapper.toDTOs(songRepo.searchSongs(searchedString));
    }
}
