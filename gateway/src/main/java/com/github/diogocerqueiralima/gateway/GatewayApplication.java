package com.github.diogocerqueiralima.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("forum-service",
                        r -> r
                                .path("/api/v1/topics/**", "/api/v1/comments/**", "/v3/api-docs/**", "/api/v1/forum/swagger-ui/**", "api/v1/forum/docs.html")
                                .uri("http://forum-service:8080")
                )
                .build();
    }

}
