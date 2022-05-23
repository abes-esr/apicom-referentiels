package fr.abes.referentiels.web;

import fr.abes.referentiels.entities.*;
import fr.abes.referentiels.service.ReferentielService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class ReferentielRestControler {
    @Autowired
    ReferentielService service;

    @Operation(summary = "Retourne la liste des langues en JSON")
    @GetMapping(value = {"/v1/langues", "/v1/langues.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Langue> langues() {
        return service.getLangues();
    }

    @Operation(summary = "Retourne la liste des langues en XML")
    @GetMapping(value = "/v1/langues.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Langue> languesXML() {
        return service.getLangues();
    }


    @Operation(summary = "Retourne la liste des pays en JSON")
    @GetMapping(value = {"/v1/pays", "/v1/pays.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Pays> pays() {
        return service.getPays();
    }

    @Operation(summary = "Retourne la liste des pays en XML")
    @GetMapping(value = "/v1/pays.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Pays> paysXML() {
        return service.getPays();
    }


    @Operation(summary = "Retourne la liste des geonames en JSON")
    @GetMapping(value = {"/v1/geonames", "/v1/geonames.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Geoname> geonames() {
        return service.getGeonames();
    }

    @Operation(summary = "Retourne la liste des geonames en XML")
    @GetMapping(value = "/v1/geonames.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Geoname> geonamesXML() {
        return service.getGeonames();
    }


    @Operation(summary = "Retourne la liste des langUris en JSON")
    @GetMapping(value = {"/v1/languris", "/v1/languris.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LangUri> languris() {
        return service.getLangUris();
    }

    @Operation(summary = "Retourne la liste des langUris en XML")
    @GetMapping(value = "/v1/languris.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<LangUri> langurisXML() {
        return service.getLangUris();
    }


    @Operation(summary = "Retourne la liste des RCR en JSON")
    @GetMapping(value = {"/v1/pcplibs", "/v1/pcplibs.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PcpLibrary> pcplibs() {
        return service.getPcpLibraries();
    }

    @Operation(summary = "Retourne la liste des RCR en XML")
    @GetMapping(value = "/v1/pcplibs.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<PcpLibrary> pcplibsXML() {
        return service.getPcpLibraries();
    }

    @Operation(summary = "Retourne la liste des RCR d'un PCP donné en pramètre")
    @GetMapping(value = "/v1/pcp2rcr/{pcp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PcpLibrary> pcp2rcr(@PathVariable String pcp) { return service.getPcpToRcr(pcp); }

    @Operation(summary = "Retourne la liste des RCR des PCPs donnés en paramètre")
    @GetMapping(value = "/v1/multipcp2rcr/{pcp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Set<PcpLibrary> pcp2rcrMulti(@PathVariable List<String> pcp) {
        return service.getPcpToRcrMulti(pcp).stream().collect(Collectors.toSet());
    }

}
