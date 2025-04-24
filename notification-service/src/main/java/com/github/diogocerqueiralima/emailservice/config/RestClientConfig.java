package com.github.diogocerqueiralima.emailservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${keycloak.admin.uri}")
    private String keycloakAdminUri;

    private final OAuth2AuthorizedClientManager authorizedClientManager;

    public RestClientConfig(OAuth2AuthorizedClientManager authorizedClientManager) {
        this.authorizedClientManager = authorizedClientManager;
    }

    @Bean
    public RestClient keyCloakRestClient() {

        OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("keycloak")
                .principal("keycloak")
                .build();

        OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);

        if (authorizedClient == null)
            throw new RuntimeException("Could not authorize client");

        return RestClient.builder()
                .baseUrl(keycloakAdminUri)
                .defaultHeader("Authorization", "Bearer " + authorizedClient.getAccessToken().getTokenValue())
                .build();
    }

}
