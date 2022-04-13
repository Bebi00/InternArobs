package com.example.musify.dto;

import org.springframework.stereotype.Component;

@Component
public class BandDTO {

  private Long id;
  private String bandName;
  private String location;
  private String startDateActivePeriod;
  private String endDateActivePeriod;


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

}
