package com.example.musify.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String countryOfOrigin;
    private Integer role;
    private Integer active;

    public UserDTO(Long id, String firstName, String lastName, String email, String countryOfOrigin, int role, int active) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.countryOfOrigin = countryOfOrigin;
        this.role = role;
        this.active = active;
    }

    public UserDTO() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return role == userDTO.role && active == userDTO.active && Objects.equals(id, userDTO.id) &&
                Objects.equals(firstName, userDTO.firstName) && Objects.equals(lastName, userDTO.lastName) &&
                Objects.equals(email, userDTO.email) && Objects.equals(countryOfOrigin, userDTO.countryOfOrigin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, countryOfOrigin, role, active);
    }
}
