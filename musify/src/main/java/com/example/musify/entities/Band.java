package com.example.musify.entities;

import javax.persistence.*;
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

    @ManyToMany(mappedBy = "bands")
    private Set<SoloArtist> soloArtists = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Artist artist;

    public Band(long id, long bandId, String bandName, String location) {
        this.id = id;
        this.bandId = bandId;
        this.bandName = bandName;
        this.location = location;
    }

    public Set<SoloArtist> getSoloArtists() {
        return soloArtists;
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
