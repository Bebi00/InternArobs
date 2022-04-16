package com.example.musify.repo;

import com.example.musify.entities.AlternativeTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlternativeTitleRepo extends JpaRepository<AlternativeTitle, Long> {
    AlternativeTitle findByAlternativeTitle(String alternativeTitle);
}
