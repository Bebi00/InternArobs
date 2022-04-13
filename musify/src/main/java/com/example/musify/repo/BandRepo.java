package com.example.musify.repo;

import com.example.musify.entities.Band;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepo extends JpaRepository<Band, Long> {

    @Override
    List<Band> findAll();

    Band findByBandName(String bandName);

    <Saved extends Band> Saved save(Saved band);

    void removeBandById(Long id);
}
