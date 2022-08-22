package fr.abes.referentiels.service;

import fr.abes.referentiels.entities.*;
import fr.abes.referentiels.repository.*;
import org.aspectj.apache.bcel.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferentielService {

    @Autowired
    private LangueRepository langueRepository;

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


    @Cacheable("langues")
    public List<Langue> getLangues() {
        return langueRepository.findAllByOrderByLabelAsc();
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

    @Cacheable("codeEcritures")
    public List<CodeEcriture> getCodeEcritures() { return codeEcritureRepository.findAllByOrderByCodeAsc();
    }

    @Cacheable("codeTranslitterations")
    public List<CodeTranslitteration> getCodeTranslitterations() { return codeTranslitterationRepository.findAllByOrderByCodeAsc();
    }

    @Cacheable("roles")
    public List<Role> getRoles() { return roleRepository.findAllByOrderByRelationship_frAsc();
    }
}
