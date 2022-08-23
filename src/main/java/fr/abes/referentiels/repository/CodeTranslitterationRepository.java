package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.CodeTranslitteration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeTranslitterationRepository extends JpaRepository<CodeTranslitteration, String> {
    List<CodeTranslitteration> findAllByOrderByCodeAsc();
}