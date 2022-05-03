package com.example.musify.dto;


import javax.validation.constraints.NotBlank;

public class PlaylistNewDTO {

  private long id;
  @NotBlank(message = "The name of the playlist can not be blank")
  private String name;
  @NotBlank(message = "The type of the playlist can not be blank")
  private String type;

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


}
