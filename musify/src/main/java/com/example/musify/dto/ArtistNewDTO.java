package com.example.musify.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import java.sql.Date;

@Component
public class ArtistNewDTO {

  private Long id;
  @NotBlank(message = "The first name of the artist can no tbe blank")
  private String firstName;
  @NotBlank(message = "The last name of the artist can no tbe blank")
  private String lastName;
  @NotBlank(message = "The stage name of the artist can no tbe blank")
  private String stageName;
  @NotBlank(message = "The birthday of the artist can no tbe blank")
  private Date birthday;
  @NotBlank(message = "The start date of the Active Period of the artist can no tbe blank")
  private String startDateActivePeriod;
  @NotBlank(message = "The end date of the Active Period of the artist can no tbe blank")
  private String endDateActivePeriod;

  public ArtistNewDTO(Long id, String firstName, String lastName, String stageName, Date birthday, String  startDateActivePeriod, String endDateActivePeriod) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.stageName = stageName;
    this.birthday = birthday;
    this.startDateActivePeriod = startDateActivePeriod;
    this.endDateActivePeriod = endDateActivePeriod;
  }

  public ArtistNewDTO() {
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


  public Date getBirthday() {
    return birthday;
  }

  public void setBirthday(Date birthday) {
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
