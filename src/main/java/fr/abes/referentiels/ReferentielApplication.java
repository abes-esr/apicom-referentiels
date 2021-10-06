package fr.abes.referentiels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
public class ReferentielApplication extends SpringBootServletInitializer {

    @Override
    public SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ReferentielApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ReferentielApplication.class, args);
    }

}

