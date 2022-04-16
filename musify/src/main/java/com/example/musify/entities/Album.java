package com.example.musify.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "albums")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private String genre;
    private LocalDate releaseDate;
    private String label;

    @OneToMany(
            mappedBy = "album",
            cascade = CascadeType.PERSIST
    )
    private List<Song> songs = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Artist artist;

    @ManyToOne(fetch = FetchType.LAZY)
    private Band band;

    public Album() {}

    public Album(long id, String title, String description, long artist, long bandId, String genre, LocalDate releaseDate, String label) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.label = label;
    }

    public void addSong(Song song) {
        songs.add(song);
        song.setAlbum(this);
    }

    public void removeSong(Song song) {
        songs.remove(song);
        song.setAlbum(null);
    }

    public void setBand(Band band) {
        this.band = band;
        band.getAlbums().add(this);
    }

    public void removeBand(Band band) {
        this.band = null;
        band.getAlbums().remove(this);
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
        artist.getAlbums().add(this);
    }

    public void removeArtist(Artist artist) {
        this.artist = null;
        artist.getAlbums().remove(this);
    }

    public List<Song> getSongs() {
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


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }


    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Artist getArtist() {
        return artist;
    }

    public Band getBand() {
        return band;
    }

}
