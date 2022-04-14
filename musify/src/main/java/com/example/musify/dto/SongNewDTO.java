package com.example.musify.dto;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SongNewDTO {

  private long id;
  private String title;
  private long contributorArtists;
  private long albumId;
  private long duration;
  private java.sql.Date creationDate;
  private long order_In_Album;
  private List<ArtistDTO> artists;



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


  public long getContributorArtists() {
    return contributorArtists;
  }

  public void setContributorArtists(long contributorArtists) {
    this.contributorArtists = contributorArtists;
  }


  public long getAlbumId() {
    return albumId;
  }

  public void setAlbumId(long albumId) {
    this.albumId = albumId;
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


  public long getOrder_In_Album() {
    return order_In_Album;
  }

  public void setOrder_In_Album(long order_In_Album) {
    this.order_In_Album = order_In_Album;
  }

  public List<ArtistDTO> getArtists() {
    return artists;
  }
}
