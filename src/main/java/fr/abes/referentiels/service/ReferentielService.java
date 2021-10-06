package fr.abes.referentiels.service;

import fr.abes.referentiels.entities.Geoname;
import fr.abes.referentiels.entities.LangUri;
import fr.abes.referentiels.entities.Langue;
import fr.abes.referentiels.entities.Pays;
import fr.abes.referentiels.repository.GeonameRepository;
import fr.abes.referentiels.repository.LangUriRepository;
import fr.abes.referentiels.repository.LangueRepository;
import fr.abes.referentiels.repository.PaysRepository;
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
}
