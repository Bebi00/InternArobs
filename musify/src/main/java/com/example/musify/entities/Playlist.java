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
  private long id;
  private String name;
  private long ownerUser;
  private long type;
  private java.sql.Date createdDate;
  private java.sql.Timestamp lastUpdatedDate;

  @ManyToMany(mappedBy = "playlists")
  private Set<Song> songs = new HashSet<>();

  @ManyToMany(mappedBy = "playlists")
  private Set<User> users = new HashSet<>();

  public Playlist(long id, String name, long ownerUser, long type, Date createdDate, Timestamp lastUpdatedDate) {
    this.id = id;
    this.name = name;
    this.ownerUser = ownerUser;
    this.type = type;
    this.createdDate = createdDate;
    this.lastUpdatedDate = lastUpdatedDate;
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public long getOwnerUser() {
    return ownerUser;
  }

  public void setOwnerUser(long ownerUser) {
    this.ownerUser = ownerUser;
  }


  public long getType() {
    return type;
  }

  public void setType(long type) {
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
