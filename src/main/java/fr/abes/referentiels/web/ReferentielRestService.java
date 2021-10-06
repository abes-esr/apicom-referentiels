package fr.abes.referentiels.web;

import fr.abes.referentiels.entities.Geoname;
import fr.abes.referentiels.entities.LangUri;
import fr.abes.referentiels.entities.Langue;
import fr.abes.referentiels.entities.Pays;
import fr.abes.referentiels.service.ReferentielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ReferentielRestService {
    @Autowired
    ReferentielService service;

    @GetMapping(value="/langues", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Langue> langues() {
        return service.getLangues();
    }

    @GetMapping(value="/pays", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Pays> pays() {
        return service.getPays();
    }

    @GetMapping(value="/geonames", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<Geoname> geonames() {
        return service.getGeonames();
    }

    @GetMapping(value="/languris", produces = { MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<LangUri> languris() {
        return service.getLangUris();
    }
}
