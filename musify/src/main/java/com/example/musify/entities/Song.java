package com.example.musify.entities;

import com.example.musify.exceptions.RepeatedSongException;

import javax.persistence.*;
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

    @Column(name = "title")
    private String title;

    @Column(name ="duration")
    private Long duration;

    @Column(name = "creation_date")
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

    public void addPlaylist(Playlist playlist) {
        playlists.add(playlist);
        if(playlist.getSongs().contains(this)){
            throw new RepeatedSongException("Song already in the playlist");
        }
        playlist.getSongs().add(this);
    }

    public void removePlaylist(Playlist playlist) {
        playlists.remove(playlist);
        playlist.getSongs().remove(this);
    }

    public void addArtist(Artist artist) {
        artists.add(artist);
        artist.getSongs().add(this);
    }

    public void removeArtist(Artist artist) {
        artists.remove(artist);
        artist.getSongs().remove(this);
    }

    public void addAlternativeTitle(AlternativeTitle alternativeTitle) {
        alternativeTitles.add(alternativeTitle);
        alternativeTitle.setSong(this);
    }

    public void removeAlternativeTitle(AlternativeTitle alternativeTitle) {
        alternativeTitles.remove(alternativeTitle);
        alternativeTitle.setSong(null);
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

    public void setId(Long id) {
        this.id = id;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Set<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(Set<Playlist> playlists) {
        this.playlists = playlists;
    }

    public List<AlternativeTitle> getAlternativeTitles() {
        return alternativeTitles;
    }

    public void setAlternativeTitles(List<AlternativeTitle> alternativeTitles) {
        this.alternativeTitles = alternativeTitles;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }
}
