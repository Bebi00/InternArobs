package com.example.musify.repo;

import com.example.musify.entities.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepo extends JpaRepository<Band, Long> {
    Band findBandByBandName(String bandName);
    Band findBandById(Long id);
    void removeBandById(Long id);

    @Query(value = "SELECT DISTINCT b FROM Band b LEFT JOIN b.artists a WHERE a.stageName LIKE %:searchedString% OR b.bandName LIKE %:searchedString%")
    List<Band> searchBandByBandNameOrArtists(@Param(value = "searchedString") String searchedString);
}
