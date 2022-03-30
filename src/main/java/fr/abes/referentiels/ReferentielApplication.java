package fr.abes.referentiels;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@OpenAPIDefinition(servers = {@Server(url = "/wsReferentiels/", description = "Default Server URL")},
        info = @Info(title = "Webservice Referentiels Sudoc", version = "1.0", description = "Ce Web Service permet d'obtenir sous forme de liste, en XML ou JSON, un referentiel"))
public class ReferentielApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ReferentielApplication.class, args);
    }

}

