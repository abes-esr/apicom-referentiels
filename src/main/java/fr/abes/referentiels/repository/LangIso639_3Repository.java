package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.LangIso639_3;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LangIso639_3Repository extends JpaRepository<LangIso639_3, String> {
    List<LangIso639_3> findAllByOrderByCodeAsc();
}