package com.example.musify.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "stage_name")
    private String stageName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "start_date_active_period")
    private String startDateActivePeriod;

    @Column(name = "end_date_active_period")
    private String endDateActivePeriod;


    @ManyToMany(mappedBy = "artists")
    private Set<Song> songs = new HashSet<>();

    @ManyToMany(mappedBy = "artists")
    private Set<Band> bands = new HashSet<>();

    @OneToMany(
            mappedBy = "artist",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Album> albums = new ArrayList<>();

    public Artist() {
    }

    public Artist(Long id, String firstName, String lastName, String stageName, LocalDate birthday, String startDateActivePeriod, String endDateActivePeriod) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.birthday = birthday;
        this.startDateActivePeriod = startDateActivePeriod;
        this.endDateActivePeriod = endDateActivePeriod;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public Set<Band> getBands() {
        return bands;
    }

    public List<Album> getAlbums() {
        return albums;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
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
