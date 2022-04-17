package com.example.musify.service;

import com.example.musify.dto.PlaylistDTO;
import com.example.musify.dto.PlaylistNewDTO;
import com.example.musify.entities.Playlist;
import com.example.musify.entities.User;
import com.example.musify.exceptions.InvalidPlaylistException;
import com.example.musify.mapper.PlaylistMapper;
import com.example.musify.repo.PlaylistRepo;
import com.example.musify.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepo playlistRepo;
    private final PlaylistMapper playlistMapper;
    private final UserRepo userRepo;

    @Autowired
    public PlaylistService(PlaylistRepo playlistRepo, PlaylistMapper playlistMapper, UserRepo userRepo) {
        this.playlistRepo = playlistRepo;
        this.playlistMapper = playlistMapper;
        this.userRepo = userRepo;
    }

    @Transactional
    public PlaylistDTO getPlaylistById(Long id) {
        return playlistMapper.toDTO(playlistRepo.findPlaylistById(id));
    }

    @Transactional
    public PlaylistDTO savePlaylist(PlaylistNewDTO playlistNewDTO) {
        Playlist newPlaylist = playlistMapper.toEntity(playlistNewDTO);
        List<Object> userInfo = (List<Object>) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepo.getById((Integer) userInfo.get(0)).get();
        newPlaylist.addUser(user);
        newPlaylist.setOwnerUser(Long.valueOf(user.getId()));
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String date = formatter.format(calendar.getTime());
        newPlaylist.setCreatedDate(LocalDate.parse(date));
        return playlistMapper.toDTO(playlistRepo.save(newPlaylist));
    }

    @Transactional
    public PlaylistDTO updatePlaylist(PlaylistNewDTO playlistNewDTO) {
        Playlist updatedPlaylist;
        try {
            updatedPlaylist = playlistRepo.findPlaylistById(playlistNewDTO.getId());
        } catch (Exception e) {
            throw new InvalidPlaylistException();
        }

        if (!updatedPlaylist.getName().equals(playlistNewDTO.getName())) {
            updatedPlaylist.setName(playlistNewDTO.getName());
        }
        if (!updatedPlaylist.getType().equals(playlistNewDTO.getType())) {
            updatedPlaylist.setType(playlistNewDTO.getType());
        }

        // for getting the update time from database in real time
        LocalDateTime localDateTime = LocalDateTime.now();
        updatedPlaylist.setLastUpdatedDate(localDateTime);
        return playlistMapper.toDTO(playlistRepo.save(updatedPlaylist));
    }

    @Transactional
    public PlaylistDTO removePlaylistById(Long id) {
        Playlist oldPlaylist = playlistRepo.findPlaylistById(id);
        if(oldPlaylist == null){
            throw new InvalidPlaylistException();
        }
        playlistRepo.removePlaylistById(id);
        return playlistMapper.toDTO(oldPlaylist);
    }
}
