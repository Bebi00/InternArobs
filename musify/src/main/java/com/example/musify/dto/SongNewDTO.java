package com.example.musify.dto;

import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class SongNewDTO {

  private long id;
  private String title;
  private long duration;
  private java.sql.Date creationDate;
  private Set<Long> artistIds;



  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }


  public java.sql.Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(java.sql.Date creationDate) {
    this.creationDate = creationDate;
  }


  public Set<Long> getArtistIds() {
    return artistIds;
  }
}
