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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private long contributorArtists;

    @Column(name = "album_id")
    private long albumId;
    private long duration;
    private java.sql.Date creationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Album album;

    @ManyToMany(cascade = {
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
            CascadeType.MERGE
    })
    @JoinTable(name = "artists_songs",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artists = new HashSet<>();

    public Song() {

    }


    public void addArtist(Artist artist) {
        artists.add(artist);
        artist.getSongs().add(this);
    }

    public void removeArtist(Artist artist) {
        artists.remove(artist);
        artist.getSongs().remove(this);
    }

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
        playlist.getSongs().add(this);
    }

    public void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);
        playlist.getSongs().remove(this);
    }


    public Song(long id, String title, long contributorArtists, long album, long duration, Date creationDate) {
        this.id = id;
        this.title = title;
        this.contributorArtists = contributorArtists;
        this.albumId = album;
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
        return albumId;
    }

    public void setAlbum(long album) {
        this.albumId = album;
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
