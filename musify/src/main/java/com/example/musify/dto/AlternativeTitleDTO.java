package com.example.musify.dto;

import org.springframework.stereotype.Component;

@Component
public class AlternativeTitleDTO {

  private Long id;
  private String alternativeTitle;
  private String language;



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

}
