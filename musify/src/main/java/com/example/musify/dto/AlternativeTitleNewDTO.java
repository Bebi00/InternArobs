package com.example.musify.dto;

import org.springframework.stereotype.Component;

@Component
public class AlternativeTitleNewDTO {

  private Long id;
  private String alternativeTitle;
  private String language;
  private Long songId;



  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

  public Long getSongId() {
    return songId;
  }

  public void setSongId(Long songId) {
    this.songId = songId;
  }
}
