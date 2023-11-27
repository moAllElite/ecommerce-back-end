package com.flyobs.ecommercebackend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private final   PasswordEncoder passwordEncoder;



     CharSequence sec="12";
    @Bean

    public  InMemoryUserDetailsManager userDetailsManager(){
         return new InMemoryUserDetailsManager(
                User.withUsername("user1").password(passwordEncoder.encode(sec)).roles("USER").build(),
                     User.withUsername("admin").password(passwordEncoder.encode(sec)).roles("USER","ADMIN").build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
        http.formLogin(httpSecurityFormLoginConfigurer -> {});
        http.authorizeHttpRequests(
                request ->
                    request.anyRequest().authenticated()

        );
        return http.build();
    }
}
