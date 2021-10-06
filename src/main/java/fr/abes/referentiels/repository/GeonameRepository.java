package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.Geoname;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GeonameRepository extends JpaRepository<Geoname, String> {
    List<Geoname> findAllByOrderByCodeAsc();
}
