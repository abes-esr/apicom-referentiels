package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.CodeEcriture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeEcritureRepository extends JpaRepository<CodeEcriture, String> {
    List<CodeEcriture> findAllByOrderByLabelAsc();
}