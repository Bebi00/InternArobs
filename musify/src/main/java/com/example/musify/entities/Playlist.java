package com.example.musify.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "playlists")
public class Playlist {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private Long ownerUser;
  private Boolean type;
  private java.sql.Date createdDate;
  private java.sql.Timestamp lastUpdatedDate;

  @ManyToMany(mappedBy = "playlists")
  private Set<Song> songs = new HashSet<>();

  @ManyToMany(mappedBy = "playlists")
  private Set<User> users = new HashSet<>();

  public Playlist(Long id, String name, Long ownerUser, Boolean type, Date createdDate, Timestamp lastUpdatedDate) {
    this.id = id;
    this.name = name;
    this.ownerUser = ownerUser;
    this.type = type;
    this.createdDate = createdDate;
    this.lastUpdatedDate = lastUpdatedDate;
  }

  public Playlist() {

  }

  public Set<Song> getSongs() {
    return songs;
  }

  public void setSongs(Set<Song> songs) {
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


  public Boolean getType() {
    return type;
  }

  public void setType(Boolean type) {
    this.type = type;
  }


  public java.sql.Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(java.sql.Date createdDate) {
    this.createdDate = createdDate;
  }


  public java.sql.Timestamp getLastUpdatedDate() {
    return lastUpdatedDate;
  }

  public void setLastUpdatedDate(java.sql.Timestamp lastUpdatedDate) {
    this.lastUpdatedDate = lastUpdatedDate;
  }

}
