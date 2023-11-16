package com.flyobs.ecommercebackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


public class WebConfig {
    @Bean
    public WebMvcConfigurer cors(){
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
