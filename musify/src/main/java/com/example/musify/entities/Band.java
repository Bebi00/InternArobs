package com.example.musify.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "bands")
public class Band {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String bandName;
    private String location;
    private String startDateActivePeriod;
    private String endDateActivePeriod;

    @ManyToMany(cascade = {
            CascadeType.MERGE
    })
    @JoinTable(name = "band_artists",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artists = new HashSet<>();

    @OneToMany(
            mappedBy = "band",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Album> albums = new ArrayList<>();

    public Band() {
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
