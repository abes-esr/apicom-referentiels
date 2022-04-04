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


}
