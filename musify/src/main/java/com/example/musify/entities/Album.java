package com.example.musify.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String description;
    private long artist;
    private String genre;
    private java.sql.Date releaseDate;
    private String label;

    @ManyToMany(mappedBy = "albums")
    private Set<Song> songs = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "artists_albums",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = {
                    @JoinColumn(name = "artist_id")
                    //, @JoinColumn(name = "band_id")
            })
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "artists_albums",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = {
            @JoinColumn(name = "band_id")
            })
    private Set<Band> bands = new HashSet<>();

    public Album() {

    }


    public void addArtist(Artist artist){
        artists.add(artist);
        artist.getAlbums().add(this);
    }

    public void addBand(Band band){
        bands.add(band);
        band.getAlbums().add(this);
    }

    public void removeAlbum(Artist artist){
        artists.remove(artist);
        artist.getAlbums().remove(this);
    }
    public void removeBand(Band band){
        bands.remove(band);
        band.getAlbums().remove(this);
    }

    public Album(long id, String title, String description, long artist, String genre, Date releaseDate, String label) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.artist = artist;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.label = label;
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


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public long getArtist() {
        return artist;
    }

    public void setArtist(long artist) {
        this.artist = artist;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public java.sql.Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(java.sql.Date releaseDate) {
        this.releaseDate = releaseDate;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
