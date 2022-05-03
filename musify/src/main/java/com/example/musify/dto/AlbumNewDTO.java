package com.example.musify.dto;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.Set;

@Component
public class AlbumNewDTO {

    private long id;
    @NotBlank(message = "The title of the album can no tbe blank")
    private String title;
    @NotBlank(message = "The description of the album can no tbe blank")
    private String description;
    @NotBlank(message = "The genre of the album can no tbe blank")
    private String genre;
    @NotBlank(message = "The release date of the album can no tbe blank")
    private LocalDate releaseDate;
    @NotBlank(message = "The label of the album can no tbe blank")
    private String label;
    @NotEmpty(message = "The album should contain at least one song")
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
