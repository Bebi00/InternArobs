package com.example.musify.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
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
    private java.sql.Date birthday;

    @Column(name = "start_date_active_period")
    private java.sql.Date startDateActivePeriod;

    @Column(name = "end_date_active_period")
    private java.sql.Date endDateActivePeriod;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "band_artists",
            joinColumns = @JoinColumn(name = "artist_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id"))
    private Set<Band> bands = new HashSet<>();

    @ManyToMany(mappedBy = "artists")
    private Set<Album> albums = new HashSet<>();


    @ManyToMany(mappedBy = "artists")
    private Set<Song> songs = new HashSet<>();

    public Artist() {
    }


    public void addBand(Band band) {
        bands.add(band);
        band.getArtists().add(this);
    }

    public void removeBand(Band band) {
        bands.remove(band);
        band.getArtists().remove(this);
    }


    public Artist(Long id, String firstName, String lastName, String stageName, Date birthday, Date startDateActivePeriod, Date endDateActivePeriod) {
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

    public Set<Album> getAlbums() {
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

    public java.sql.Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public java.sql.Date getStartDateActivePeriod() {
        return startDateActivePeriod;
    }

    public void setStartDateActivePeriod(java.sql.Date startDateActivePeriod) {
        this.startDateActivePeriod = startDateActivePeriod;
    }

    public java.sql.Date getEndDateActivePeriod() {
        return endDateActivePeriod;
    }

    public void setEndDateActivePeriod(java.sql.Date endDateActivePeriod) {
        this.endDateActivePeriod = endDateActivePeriod;
    }

}
