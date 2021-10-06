package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.LangUri;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LangUriRepository extends JpaRepository<LangUri, String> {
    List<LangUri> findAllByOrderByCodeAsc();
}
