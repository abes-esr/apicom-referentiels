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

    //TODO : supprimer les urls langues, langues.json et langues.xml, pour utiliser à la place iso639-2B
    @Operation(summary = "Retourne la liste des langues en JSON")
    @GetMapping(value = {"/v1/langues", "/v1/langues.json", "/v1/iso639-2B", "/v1/iso639-2B.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Langue> langues() {
        return service.getLangues();
    }

    @Operation(summary = "Retourne la liste des langues en XML")
    @GetMapping(value = {"/v1/langues.xml", "/v1/iso639-2B.xml"}, produces = MediaType.APPLICATION_XML_VALUE)
    public List<Langue> languesXML() {
        return service.getLangues();
    }


    @Operation(summary = "Retourne la liste des langues ISO639_3 en JSON")
    @GetMapping(value = {"/v1/iso639-3", "/v1/iso639-3.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LangIso639_3> langIso6393s() {
        return service.getLangsISO639_3();
    }

    @Operation(summary = "Retourne la liste des langues ISO639_3 en XML")
    @GetMapping(value = "/v1/iso639-3.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<LangIso639_3> langIso6393sXML() {
        return service.getLangsISO639_3();
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

    @Operation(summary = "Retourne la liste des RCR d'une liste de PCP donnés en paramètre")
    @GetMapping(value = "/v1/pcp2rcr/{pcp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<String> pcp2rcr(@PathVariable List<String> pcp) { return service.getPcpToRcr(pcp); }



    @Operation(summary = "Retourne la liste des codes d'écriture en JSON")
    @GetMapping(value = {"/v1/ecritures", "/v1/ecritures.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CodeEcriture> ecritures() {
        return service.getCodesEcritures();
    }

    @Operation(summary = "Retourne la liste des codes d'écriture en XML")
    @GetMapping(value = "/v1/ecritures.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<CodeEcriture> ecrituresXML() {
        return service.getCodesEcritures();
    }


    @Operation(summary = "Retourne la liste des codes de translitteration en JSON")
    @GetMapping(value = {"/v1/translitterations", "/v1/translitterations.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CodeTranslitteration> translitterations() { return service.getCodesTranslitterations();}

    @Operation(summary = "Retourne la liste des codes de translitteration en XML")
    @GetMapping(value = "/v1/translitterations.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<CodeTranslitteration> translitterationsXML() {
        return service.getCodesTranslitterations();
    }


    @Operation(summary = "Retourne la liste des roles / codes de fonction en JSON")
    @GetMapping(value = {"/v1/roles", "/v1/roles.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Role> roles() { return service.getRoles();}

    @Operation(summary = "Retourne la liste des roles / codes de fonction en XML")
    @GetMapping(value = "/v1/roles.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Role> rolesXML() { return service.getRoles();}


    @Operation(summary = "Retourne la liste des codes musicaux en JSON")
    @GetMapping(value = {"/v1/musicaux", "/v1/musicaux.json"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CodeMusical> codesMusicaux() { return service.getCodesMusicaux();}

    @Operation(summary = "Retourne la liste des codes musicaux en XML")
    @GetMapping(value = "/v1/musicaux.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<CodeMusical> codesMusicauxXML() { return service.getCodesMusicaux();}
}
