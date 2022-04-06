package com.example.musify.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private long contributorArtists;
    private long album;
    private long duration;
    private java.sql.Date creationDate;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "songs_albums",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id"))
    private Set<Album> albums = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "songs_playlists",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "playlist_id"))
    private Set<Playlist> playlists = new HashSet<>();

    @OneToMany(
            mappedBy = "song",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<AlternativeTitle> alternativeTitles = new ArrayList<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "artists_songs",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artists = new HashSet<>();

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "artists_songs",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id"))
    private Set<Band> bands = new HashSet<>();


    public void addArtist(Artist artist) {
        artists.add(artist);
        artist.getSongs().add(this);
    }

    public void removeArtist(Artist artist) {
        artists.remove(artist);
        artist.getSongs().remove(this);
    }

    public void addBand(Band band) {
        bands.add(band);
        band.getSongs().add(this);
    }

    public void removeBand(Band band) {
        bands.remove(band);
        band.getSongs().remove(this);
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
        playlist.getSongs().add(this);
    }

    public void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);
        playlist.getSongs().remove(this);
    }

    public void addAlbum(Album album) {
        albums.add(album);
        album.getSongs().add(this);
    }

    public void removeAlbum(Album album) {
        albums.remove(album);
        album.getSongs().remove(this);
    }


    public Song(long id, String title, long contributorArtists, long album, long duration, Date creationDate) {
        this.id = id;
        this.title = title;
        this.contributorArtists = contributorArtists;
        this.album = album;
        this.duration = duration;
        this.creationDate = creationDate;
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


    public long getContributorArtists() {
        return contributorArtists;
    }

    public void setContributorArtists(long contributorArtists) {
        this.contributorArtists = contributorArtists;
    }


    public long getAlbum() {
        return album;
    }

    public void setAlbum(long album) {
        this.album = album;
    }


    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }


    public java.sql.Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(java.sql.Date creationDate) {
        this.creationDate = creationDate;
    }

}
