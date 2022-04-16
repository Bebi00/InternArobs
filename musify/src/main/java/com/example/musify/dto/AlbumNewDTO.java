package com.example.musify.dto;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class AlbumNewDTO {

    private long id;
    private String title;
    private String description;
    private String genre;
    private LocalDate releaseDate;
    private String label;
    private Set<Long> songIds;
    private Long artistId;
    private Long bandId;

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Set<Long> getSongIds() {
        return songIds;
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


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public Long getArtistId() {
        return artistId;
    }

    public Long getBandId() {
        return bandId;
    }
}
