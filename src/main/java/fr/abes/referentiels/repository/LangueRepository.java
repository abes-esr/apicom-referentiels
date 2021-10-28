package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.Langue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LangueRepository extends JpaRepository<Langue, String> {
    @Query( value = "select i.code_1 as codecourt, l.* from LANG_LABEL l left join LANG_ISO_639_2_TO_1 i ON l.code=i.code_2 order by l.label asc", nativeQuery = true)
    List<Langue> findAllByOrderByLabelAsc();
}
