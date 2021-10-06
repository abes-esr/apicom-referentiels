package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.Langue;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LangueRepository extends JpaRepository<Langue, String> {
    List<Langue> findAllByOrderByLabelAsc();
}
