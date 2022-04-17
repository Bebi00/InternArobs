package com.example.musify.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class PlaylistDTO {

  private long id;
  private String name;
  private String type;
  private Long ownerUser;
  private LocalDate createdDate;
  private LocalDateTime lastUpdatedDate;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }


  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  public Long getOwnerUser() {
    return ownerUser;
  }

  public void setOwnerUser(Long ownerUser) {
    this.ownerUser = ownerUser;
  }

  public LocalDateTime getLastUpdatedDate() {
    return lastUpdatedDate;
  }

  public void setLastUpdatedDate(LocalDateTime lastUpdatedDate) {
    this.lastUpdatedDate = lastUpdatedDate;
  }

}
