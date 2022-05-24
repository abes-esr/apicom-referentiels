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
    private LangueRepository langueRepository;

    @Autowired
    private PaysRepository paysRepository;

    @Autowired
    private GeonameRepository geonameRepository;

    @Autowired
    private LangUriRepository langUriRepository;

    @Autowired
    private PcpLibraryRepository pcpLibraryRepository;


    @Cacheable("langues")
    public List<Langue> getLangues() {
        return langueRepository.findAllByOrderByLabelAsc();
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

}
