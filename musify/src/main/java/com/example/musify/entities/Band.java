package com.example.musify.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bands")
public class Band {

    @Id
    @GeneratedValue
    private long id;
    private long bandId;
    private String bandName;
    private String location;
    private java.sql.Date startDateActivePeriod;
    private java.sql.Date endDateActivePeriod;

    @ManyToMany(mappedBy = "bands")
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany(mappedBy = "bands")
    private Set<Album> albums = new HashSet<>();

    @ManyToMany(mappedBy = "bands")
    private Set<Song> songs = new HashSet<>();

    public Band(long id, long bandId, String bandName, String location, Date startDateActivePeriod, Date endDateActivePeriod) {
        this.id = id;
        this.bandId = bandId;
        this.bandName = bandName;
        this.location = location;
        this.startDateActivePeriod = startDateActivePeriod;
        this.endDateActivePeriod = endDateActivePeriod;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public Set<Album> getAlbums() {
        return albums;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getBandId() {
        return bandId;
    }

    public void setBandId(long bandId) {
        this.bandId = bandId;
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

}
