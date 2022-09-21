package fr.abes.referentiels.service;

import fr.abes.referentiels.entities.*;
import fr.abes.referentiels.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferentielService {

    @Autowired
    private LangIso639_2BRepository langIso639_2BRepository;

    @Autowired
    private LangIso639_3Repository langIso639_3Repository;

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private GeonameRepository geonameRepository;

    @Autowired
    private LangUriRepository langUriRepository;

    @Autowired
    private PcpLibraryRepository pcpLibraryRepository;

    @Autowired
    private CodeEcritureRepository codeEcritureRepository;

    @Autowired
    private CodeTranslitterationRepository codeTranslitterationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CodeMusicalRepository codeMusicalRepository;


    @Cacheable("langISO639_2B")
    public List<LangIso639_2B> getLangsISO639_2B() {
        return langIso639_2BRepository.findAllByOrderByLabelAsc();
    }

    @Cacheable("langISO639_3")
    public List<LangIso639_3> getLangsISO639_3() {
        return langIso639_3Repository.findAllByOrderByCodeAsc();
    }

    @Cacheable("pays")
    public List<Pays> getPays() {
        return paysRepository.findAllByOrderByLabelAsc();
    }

    @Cacheable("geonames")
    public List<Geoname> getGeonames() { return geonameRepository.findAllByOrderByCodeAsc();
    }

    @Cacheable("languris")
    public List<LangUri> getLangUris() { return langUriRepository.findAllByOrderByCodeAsc();
    }

    @Cacheable("pcplibraries")
    public List<PcpLibrary> getPcpLibraries() { return pcpLibraryRepository.findAllByOrderByRcrAsc();
    }

    @Cacheable("pcp2rcr")
    public List<String> getPcpToRcr(List<String> pcp) { return pcpLibraryRepository.findRcrbyPcp(pcp);  }


    @Cacheable("codesEcritures")
    public List<CodeEcriture> getCodesEcritures() { return codeEcritureRepository.findAllByOrderByLabelAsc();
    }

    @Cacheable("codesTranslitterations")
    public List<CodeTranslitteration> getCodesTranslitterations() { return codeTranslitterationRepository.findAllByOrderByCodeAsc();
    }

    @Cacheable("roles")
    public List<Role> getRoles() { return roleRepository.findAllByOrderByRelationshipFrAsc();
    }

    @Cacheable("codesMusicaux")
    public List<CodeMusical> getCodesMusicaux() { return codeMusicalRepository.findAllByOrderByCodeAsc();
    }
}
