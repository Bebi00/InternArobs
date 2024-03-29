package com.example.musify.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserNewDTO {

  @NotBlank(message = "First Name cannot be blank")
  private String firstName;
  @NotBlank(message = "Last Name cannot be blank")
  private String lastName;
  @NotBlank(message = "Email cannot be blank")
  private String email;

  @Size(min = 6,message = "Password should be at least 6 characters long")
  private String password;
  @NotBlank(message = "The country of origin can not be blank")
  private String countryOfOrigin;

  public UserNewDTO(String firstName, String lastName, String email, String password, String countryOfOrigin) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.countryOfOrigin = countryOfOrigin;
  }

  public UserNewDTO() {
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getCountryOfOrigin() {
    return countryOfOrigin;
  }

  public void setCountryOfOrigin(String countryOfOrigin) {
    this.countryOfOrigin = countryOfOrigin;
  }

}


