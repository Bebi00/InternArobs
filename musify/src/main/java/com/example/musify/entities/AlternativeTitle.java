package com.example.musify.entities;

import javax.persistence.*;

@Entity
@Table(name = "alternative_titles")
public class AlternativeTitle {

  @Id
  @GeneratedValue
  private long id;
  private long songId;
  private String alternativeTitle;
  private String language;

  @ManyToOne(fetch = FetchType.LAZY)
  private Song song;

  public AlternativeTitle(long id, long songId, String alternativeTitle, String language) {
    this.id = id;
    this.songId = songId;
    this.alternativeTitle = alternativeTitle;
    this.language = language;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public long getSongId() {
    return songId;
  }

  public void setSongId(long songId) {
    this.songId = songId;
  }


  public String getAlternativeTitle() {
    return alternativeTitle;
  }

  public void setAlternativeTitle(String alternativeTitle) {
    this.alternativeTitle = alternativeTitle;
  }


  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

}
