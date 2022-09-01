package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    @Query(value = "SELECT * FROM FNCT_MARC21 order by NLSSORT(relationship_fr,'NLS_SORT=FRENCH')", nativeQuery = true)
    List<Role> findAllByOrderByRelationshipFrAsc();
}