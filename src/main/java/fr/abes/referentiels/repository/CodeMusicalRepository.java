package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.CodeMusical;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeMusicalRepository extends JpaRepository<CodeMusical, String> {
    List<CodeMusical> findAllByOrderByCodeAsc();
}