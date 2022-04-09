package com.example.musify.entities;

import javax.persistence.*;

@Entity
@Table(name = "alternative_titles")
public class AlternativeTitle {

  @Id
  @GeneratedValue
  private long id;

  private String alternativeTitle;
  private String language;

  @ManyToOne(fetch = FetchType.LAZY)
  private Song song;

  public AlternativeTitle(long id, String alternativeTitle, String language) {
    this.id = id;
    this.alternativeTitle = alternativeTitle;
    this.language = language;
  }

  public AlternativeTitle() {

  }

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
