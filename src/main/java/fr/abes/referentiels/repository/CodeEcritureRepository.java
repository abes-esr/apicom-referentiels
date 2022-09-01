package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.CodeEcriture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeEcritureRepository extends JpaRepository<CodeEcriture, String> {
    @Query(value = "SELECT * FROM code_ecriture order by NLSSORT(label,'NLS_SORT=FRENCH')", nativeQuery = true)
    List<CodeEcriture> findAllByOrderByLabelAsc();
}