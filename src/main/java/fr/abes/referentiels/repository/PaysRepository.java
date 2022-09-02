package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.Pays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PaysRepository extends JpaRepository<Pays, String> {
    List<Pays> findAllByOrderByLabelAsc();
}
