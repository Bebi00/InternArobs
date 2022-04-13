package com.example.musify.entities;

import javax.persistence.*;
import java.util.HashSet;
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

    @ManyToMany(mappedBy = "bands")
    private Set<Album> albums = new HashSet<>();

    @ManyToMany(mappedBy = "bands")
    private Set<Song> songs = new HashSet<>();

    public Band(long id, String bandName, String location, String startDateActivePeriod, String endDateActivePeriod) {
        this.id = id;
        this.bandName = bandName;
        this.location = location;
        this.startDateActivePeriod = startDateActivePeriod;
        this.endDateActivePeriod = endDateActivePeriod;
    }

    public Band() {

    }

    public void addArtist(Artist artist) {
        artists.add(artist);
        artist.getBands().add(this);
    }

    public void removeArtist(Artist artist) {
        artists.remove(artist);
        artist.getBands().remove(this);
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
