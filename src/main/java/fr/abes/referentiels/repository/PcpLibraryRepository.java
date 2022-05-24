package fr.abes.referentiels.repository;

import fr.abes.referentiels.entities.PcpLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PcpLibraryRepository extends JpaRepository<PcpLibrary, String> {
    @Query( value = "select rcr,nom_court as label from view_lst_bibs " +
                    "where (substr(rcr, 1,3)!='999' and length(rcr)=9 and iln!=199 and " +
                            "(iln<350 or (iln>=401 and iln<=499))) or rcr='341725298' or rcr='341725299' " +
                            "order by rcr", nativeQuery = true)
    List<PcpLibrary> findAllByOrderByRcrAsc();

    @Query(value = "select distinct rcr from PCP where PCP in (:pcp)"
            , nativeQuery = true)
    List<String> findRcrbyPcp(@Param(value = "pcp") List<String> pcp);

}

