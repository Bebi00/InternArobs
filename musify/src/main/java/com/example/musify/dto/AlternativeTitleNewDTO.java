package com.example.musify.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Component
public class AlternativeTitleNewDTO {

  private Long id;
  @NotBlank(message = "The title can not be blank")
  private String alternativeTitle;
  @NotBlank(message = "The language can not be blank")
  private String language;
  @NotEmpty(message = "The title should have at least one songId")
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
