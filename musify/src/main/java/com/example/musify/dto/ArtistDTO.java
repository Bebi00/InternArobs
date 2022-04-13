package com.example.musify.dto;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class ArtistDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String stageName;
  private java.sql.Date birthday;
  private String startDateActivePeriod;
  private String endDateActivePeriod;

  public ArtistDTO(Long id, String firstName, String lastName, String stageName, Date birthday, String  startDateActivePeriod, String endDateActivePeriod) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.stageName = stageName;
    this.birthday = birthday;
    this.startDateActivePeriod = startDateActivePeriod;
    this.endDateActivePeriod = endDateActivePeriod;
  }

  public ArtistDTO() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }


  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }


  public String getStageName() {
    return stageName;
  }

  public void setStageName(String stageName) {
    this.stageName = stageName;
  }


  public java.sql.Date getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Date birthday) {
    this.birthday = birthday;
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
