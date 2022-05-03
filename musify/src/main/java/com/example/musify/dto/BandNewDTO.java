package com.example.musify.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Component
public class BandNewDTO {

  private Long id;
  @NotBlank(message = "The Band name of the band can not be blank")
  private String bandName;
  @NotBlank(message = "The Band location of the band can not be blank")
  private String location;
  @NotBlank(message = "The start date of the active period of the band can not be blank")
  private String startDateActivePeriod;
  @NotBlank(message = "The end date of the active period of the band can not be blank")
  private String endDateActivePeriod;
  @NotEmpty(message = "The Band should contain at least one artist member")
  private Set<Long> artistsIds;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getBandName() {
    return bandName;
  }

  public void setBandName(String bandName) {
    this.bandName = bandName;
  }


  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }


  public String getStartDateActivePeriod() {
    return startDateActivePeriod;
  }

  public void setStartDateActivePeriod(String startDateActivePeriod) {
    this.startDateActivePeriod = startDateActivePeriod;
  }


  public String getEndDateActivePeriod() {
    return endDateActivePeriod;
  }

  public void setEndDateActivePeriod(String endDateActivePeriod) {
    this.endDateActivePeriod = endDateActivePeriod;
  }

  public Set<Long> getArtistsIds() {
    return artistsIds;
  }
}
