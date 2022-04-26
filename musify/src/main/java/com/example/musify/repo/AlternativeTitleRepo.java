package com.example.musify.repo;

import com.example.musify.entities.AlternativeTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlternativeTitleRepo extends JpaRepository<AlternativeTitle, Long> {
    AlternativeTitle findByAlternativeTitleContaining(String alternativeTitle);
    List<AlternativeTitle> findAlternativeTitleByAlternativeTitleContaining(String alternativeTitle);
}
