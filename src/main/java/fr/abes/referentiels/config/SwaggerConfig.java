package fr.abes.referentiels.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("fr.abes.referentiels.web"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metadata());

    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Webservice Referentiels Sudoc")
                .description("Ce Web Service permet d'obtenir sous forme de liste, en XML ou JSON, un référentiel<br />")
                .version("1.0.0")
                .contact(new Contact("ACT", "https://github.com/abes-esr/apicom-referentiels", "act@abes.fr")).build();
    }
}
