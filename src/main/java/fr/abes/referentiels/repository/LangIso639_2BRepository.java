package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.LangIso639_2B;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LangIso639_2BRepository extends JpaRepository<LangIso639_2B, String> {
    @Query( value = "select i.code_1 as codecourt, l.* " +
                    "from LANG_LABEL l left join LANG_ISO_639_2_TO_1 i ON l.code=i.code_2 " +
                    "order by l.label asc", nativeQuery = true)
    List<LangIso639_2B> findAllByOrderByLabelAsc();
}
