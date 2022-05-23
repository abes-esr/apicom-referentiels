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

    @Query(value = "select rcr, nom_court as label " +
            "from (" +
            "    select lib1.rcr, lib1.nom_court " +
            "    from (" +
            "        select v.rcr,v.nom_court " +
            "        from view_lst_bibs v, lib_profile l " +
            "        where v.rcr=l.rcr " +
            "        and exists" +
            "            (select 1 from biblio_table_frbr_exemplaire b, biblio_table_frbr_exemplaire c " +
            "            where b.tag='930$b' and v.rcr=b.DATAS " +
            "            and b.exemplaire=c.exemplaire and c.tag='930$z' and c.DATAS=:pcp)" +
            "    ) lib1 " +
            "    UNION " +
            "    select lib2.rcr, lib2.nom_court from (" +
            "        select v.rcr,v.nom_court " +
            "        from view_lst_bibs v, lib_profile l " +
            "        where v.rcr=l.rcr " +
            "        and exists" +
            "            (select 1 from biblio_table_frbr_exemplaire b, biblio_table_frbr_exemplaire c " +
            "            where b.tag='930$b' and v.rcr=b.DATAS " +
            "            and b.exemplaire=c.exemplaire and c.tag='930$z' and c.DATAS=:pcp)" +
            "        and not exists (select 1 from AUT_TABLE_FRBR_7XX a where a.ppn=v.ppn and a.TAG='701$a' and a.POSFIELD=1 and a.POSSUBFIELD=1 )" +
            "    ) lib2" +
            ")", nativeQuery = true)
    List<PcpLibrary> findRcrbyPcp(@Param(value = "pcp") String pcp);

    @Query(value = "select rcr, nom_court as label " +
            "from (" +
            "    select lib1.rcr, lib1.nom_court " +
            "    from (" +
            "        select v.rcr,v.nom_court " +
            "        from view_lst_bibs v, lib_profile l " +
            "        where v.rcr=l.rcr " +
            "        and exists" +
            "            (select 1 from biblio_table_frbr_exemplaire b, biblio_table_frbr_exemplaire c " +
            "            where b.tag='930$b' and v.rcr=b.DATAS " +
            "            and b.exemplaire=c.exemplaire and c.tag='930$z' and c.DATAS in (:pcps))" +
            "    ) lib1 " +
            "    UNION " +
            "    select lib2.rcr, lib2.nom_court from (" +
            "        select v.rcr,v.nom_court " +
            "        from view_lst_bibs v, lib_profile l " +
            "        where v.rcr=l.rcr " +
            "        and exists" +
            "            (select 1 from biblio_table_frbr_exemplaire b, biblio_table_frbr_exemplaire c " +
            "            where b.tag='930$b' and v.rcr=b.DATAS " +
            "            and b.exemplaire=c.exemplaire and c.tag='930$z' and c.DATAS in (:pcps))" +
            "        and not exists (select 1 from AUT_TABLE_FRBR_7XX a where a.ppn=v.ppn and a.TAG='701$a' and a.POSFIELD=1 and a.POSSUBFIELD=1 )" +
            "    ) lib2" +
            ")", nativeQuery = true)
    List<PcpLibrary> findRcrbyPcpMulti(List<String> pcps);
}

