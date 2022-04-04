package com.example.musify.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "artists")
public class Artist {

    @Id
    @GeneratedValue
    private long id;
    private java.sql.Date startDateActivePeriod;
    private java.sql.Date endDateActivePeriod;
    private long type;

    @ManyToMany(mappedBy = "artists")
    private Set<Song> songs = new HashSet<>();


    @ManyToOne(fetch = FetchType.LAZY)
    private SoloArtist soloArtist;

    @OneToMany(mappedBy = "artist",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Band> bands = new ArrayList<>();

    public Artist(long id, Date startDateActivePeriod, Date endDateActivePeriod, long type) {
        this.id = id;
        this.startDateActivePeriod = startDateActivePeriod;
        this.endDateActivePeriod = endDateActivePeriod;
        this.type = type;
    }

    public Set<Song> getSongs() {
        return songs;
    }

    public void setSongs(Set<Song> songs) {
        this.songs = songs;
    }

    public SoloArtist getSoloArtist() {
        return soloArtist;
    }

    public void setSoloArtist(SoloArtist soloArtist) {
        this.soloArtist = soloArtist;
    }

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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


    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }

}
