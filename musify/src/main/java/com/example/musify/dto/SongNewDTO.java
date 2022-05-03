package com.example.musify.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Component
public class SongNewDTO {

  @NotBlank(message = "The title of the song can not be blank")
  private String title;
  @NotBlank(message = "The duration of the song can not be blank")

  private long duration;
  @NotBlank(message = "The creation date of the song can not be blank")
  private LocalDate creationDate;
  @NotEmpty(message = "The song should contain at least one contributor artist")
  private Set<Long> artistIds;

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


  public LocalDate getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDate creationDate) {
    this.creationDate = creationDate;
  }


  public Set<Long> getArtistIds() {
    return artistIds;
  }
}
