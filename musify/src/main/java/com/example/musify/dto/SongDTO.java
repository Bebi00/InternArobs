package com.example.musify.dto;

import org.springframework.stereotype.Component;

@Component
public class SongDTO {
  private Long id;
  private String title;
  private long duration;
  private java.sql.Date creationDate;
  private long orderInAlbum;


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


  public long getOrderInAlbum() {
    return orderInAlbum;
  }

  public void setOrderInAlbum(long orderInAlbum) {
    this.orderInAlbum = orderInAlbum;
  }

}