package fr.abes.referentiels.referentiels;

import fr.abes.referentiels.service.ReferentielService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ReferentielsApplicationTests {
    @Autowired
    private ReferentielService service;

    @Test
    @DisplayName("getGeonamesOk")
    void getGeonamesOk() {
        assertThat(service.getGeonames().size()>100);
        assertThat(service.getGeonames()).extracting("code").containsSequence("FR");
    }

    @Test
    @DisplayName("getLanguesOk")
    void getLanguesOk() {
        assertThat(service.getLangues().size()>100);
        assertThat(service.getLangues()).extracting("code").containsSequence("fre");
    }

    @Test
    @DisplayName("getLangUrisOk")
    void getLangUrisOk() {
        assertThat(service.getLangUris().size()>100);
        assertThat(service.getLangUris()).extracting("code").containsSequence("fre");
    }

    @Test
    @DisplayName("getPaysOk")
    void getPaysOk() {
        assertThat(service.getPays().size()>100);
        assertThat(service.getPays()).extracting("code").containsSequence("FR");
    }

    @Test
    @DisplayName("getPcpLibsOk")
    void getPcpLibsOk() {
        assertThat(service.getPcpLibraries().size()>100);
    }

    @Test
    @DisplayName("getLangsISO639_3Ok")
    void getLangsISO639_3Ok() {
        assertThat(service.getLangsISO639_3().size()>100);
        assertThat(service.getLangsISO639_3()).extracting("code").containsSequence("fra");
    }

    @Test
    @DisplayName("getCodesEcrituresOk")
    void getCodesEcrituresOk() {
        assertThat(service.getCodesEcritures().size()>10);
        assertThat(service.getCodesEcritures()).extracting("code").containsSequence("ba");
    }

    @Test
    @DisplayName("getCodesTranslitterationsOk")
    void getCodesTranslitterationsOk() {
        assertThat(service.getCodesTranslitterations().size()>5);
        assertThat(service.getCodesTranslitterations()).extracting("code").containsSequence("a");
    }

    @Test
    @DisplayName("getRolesOk")
    void getRolesOk() {
        assertThat(service.getRoles().size()>100);
        assertThat(service.getRoles()).extracting("code").containsSequence("070");
    }

    @Test
    @DisplayName("getCodesMusicauxOk")
    void getCodesMusicauxOk() {
        assertThat(service.getCodesMusicaux().size()>100);
        assertThat(service.getCodesMusicaux()).extracting("code").containsSequence("wcl");
    }

}
