package com.example.musify.repo;

import com.example.musify.entities.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BandRepo extends JpaRepository<Band, Long> {
    Band findByBandName(String bandName);
    void removeBandById(Long id);
}
