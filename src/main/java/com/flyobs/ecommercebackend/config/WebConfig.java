package com.flyobs.ecommercebackend.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {
    @Bean
    public @NotNull WebMvcConfigurer cors(){
        return  new WebMvcConfigurer() {
            //sur url http://localhost:4200
            // on permet l'éxécution des méthodes GET , POST, DELETE & PUT
            @Override
            public void addCorsMappings( CorsRegistry registry) {
                WebMvcConfigurer.super.addCorsMappings(registry);
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedHeaders(
                                HttpHeaders.CONTENT_TYPE,
                                "X-CSRF-TOKEN"
                        )
                        .allowedMethods(
                                HttpMethod.GET.name(),
                                HttpMethod.POST.name(),
                                HttpMethod.PUT.name(),
                                HttpMethod.DELETE.name()
                        )
                        .allowCredentials(true);

            }
        };
    }
}
