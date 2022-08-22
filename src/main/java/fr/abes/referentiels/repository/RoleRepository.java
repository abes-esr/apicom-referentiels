package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String> {
    List<Role> findAllByOrderByRelationship_frAsc();
}