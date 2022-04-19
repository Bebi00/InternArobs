package com.example.musify.entities;

import com.example.musify.exceptions.RepeatedSongException;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "playlists")
public class Playlist {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Long ownerUser;
  private String type;
  private LocalDate createdDate;
  private LocalDateTime lastUpdatedDate;

  @ManyToMany(cascade = {
          CascadeType.MERGE
  })
  @JoinTable(name = "songs_playlists",
          joinColumns = @JoinColumn(name = "playlist_id"),
          inverseJoinColumns = @JoinColumn(name = "song_id"))
  @OrderColumn(name = "position_in_playlist")
  private List<Song> songs = new LinkedList<>();

  @ManyToMany(cascade = {
          CascadeType.MERGE
  })
  @JoinTable(name = "users_playlists",
          joinColumns = @JoinColumn(name = "playlist_id"),
          inverseJoinColumns = @JoinColumn(name = "user_id"))
  private Set<User> users =new HashSet<>();

  public Playlist(Long id, String name, Long ownerUser, String type, LocalDate createdDate, LocalDateTime lastUpdatedDate) {
    this.id = id;
    this.name = name;
    this.ownerUser = ownerUser;
    this.type = type;
    this.createdDate = createdDate;
    this.lastUpdatedDate = lastUpdatedDate;
  }

  public Playlist() {

  }

  public void addUser(User user){
    users.add(user);
    user.getPlaylists().add(this);
  }

  public void removeUser(User user){
    users.remove(user);
    user.getPlaylists().remove(this);
  }

  public void addSong(Song song) {
    if(this.getSongs().contains(song)){
      throw new RepeatedSongException("Song already in the playlist");
    }
    songs.add(song);
    song.getPlaylists().add(this);
  }

  public void removeSong(Song song) {
    songs.remove(song);
    song.getPlaylists().remove(this);
  }

  public List<Song> getSongs() {
    return songs;
  }

  public void setSongs(LinkedList<Song> songs) {
    this.songs = songs;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public Long getOwnerUser() {
    return ownerUser;
  }

  public void setOwnerUser(Long ownerUser) {
    this.ownerUser = ownerUser;
  }


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }


  public LocalDateTime getLastUpdatedDate() {
    return lastUpdatedDate;
  }

  public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
    this.lastUpdatedDate = lastUpdatedDate;
  }

}
