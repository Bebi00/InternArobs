package com.example.musify.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "solo_artist")
public class SoloArtist {

    @Id
    @GeneratedValue
    private long id;
    private long artistsId;
    private String firstName;
    private String lastName;
    private String stageName;
    private java.sql.Date birthday;

    @OneToMany(mappedBy = "soloArtist",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Artist> artists = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "band_artists",
    joinColumns = @JoinColumn(name = "solo_artist_id"),
    inverseJoinColumns = @JoinColumn(name = "band_id"))
    private Set<Band> bands = new HashSet<>();

    public void addBand(Band band){
        bands.add(band);
        band.getSoloArtists().add(this);
    }

    public void removeBand(Band band){
        bands.remove(band);
        band.getSoloArtists().remove(this);
    }

    public SoloArtist(long id, long artistsId, String firstName, String lastName, String stageName, Date birthday) {
        this.id = id;
        this.artistsId = artistsId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stageName = stageName;
        this.birthday = birthday;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public long getArtistsId() {
        return artistsId;
    }

    public void setArtistsId(long artistsId) {
        this.artistsId = artistsId;
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

}
