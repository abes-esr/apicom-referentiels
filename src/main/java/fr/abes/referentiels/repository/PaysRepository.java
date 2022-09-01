package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaysRepository extends JpaRepository<Pays, String> {
    @Query(value = "SELECT * FROM COUNTRY_LABEL order by NLSSORT(label,'NLS_SORT=FRENCH')", nativeQuery = true)
    List<Pays> findAllByOrderByLabelAsc();
}
