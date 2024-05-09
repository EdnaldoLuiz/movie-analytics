package com.ednaldoluiz.moviedash.config;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

    private static final String BEARER_KEY = "bearer-key";
    private static final String BEARER = "bearer";
    private static final String JWT = "JWT";
    private static final String API_TITLE = "Movie Dash API";
    private static final String API_DESCRIPTION = "API para consulta de filmes baseada no TMDB.";
    private static final String API_VERSION = "1.0.0";
    private static final String CONTACT_NAME = "Ednaldo Luiz";
    private static final String CONTACT_EMAIL = "contatoednaldoluiz@gmail.com";
    private static final String CONTACT_URL = "https://ednaldo-luiz.vercel.app";
    private static final String LICENSE_NAME = "Licença MIT";
    private static final String LICENSE_URL = "https://opensource.org/licenses/MIT";
    private static final String LOCALHOST = "http://localhost:";
    private static final TreeMap<String, String> SERVERS = new TreeMap<>();

    static {
        SERVERS.put(LOCALHOST + 8080, "Servidor local");
        SERVERS.put(LOCALHOST + 8081, "Servidor docker");
        SERVERS.put(LOCALHOST + 8082, "Servidor produção");
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(getComponents())
                .info(getApiInfo())
                .addSecurityItem(new SecurityRequirement().addList(BEARER_KEY)).servers(getServers());
    }

    private Components getComponents() {
        return new Components()
                .addSecuritySchemes(BEARER_KEY,
                        new SecurityScheme().type(SecurityScheme.Type.HTTP)
                                .scheme(BEARER)
                                .bearerFormat(JWT));
    }

    private Info getApiInfo() {
        return new Info()
                .title(API_TITLE)
                .description(API_DESCRIPTION)
                .version(API_VERSION)
                .contact(getContact())
                .license(getLicense());
    }

    private Contact getContact() {
        return new Contact()
                .name(CONTACT_NAME)
                .email(CONTACT_EMAIL)
                .url(CONTACT_URL);
    }

    private List<Server> getServers() {
        List<Server> servers = new ArrayList<>();
        SERVERS.forEach((url, description) -> servers.add(new Server()
                .url(url)
                .description(description)));
        return servers;
    }

    private License getLicense() {
        return new License()
                .name(LICENSE_NAME)
                .url(LICENSE_URL);
    }
}