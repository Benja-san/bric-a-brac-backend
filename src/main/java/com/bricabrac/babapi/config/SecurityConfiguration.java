package com.bricabrac.babapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
// @EnableMethodSecurity enables @PreAuthorize annotation in controllers
@EnableMethodSecurity
//Enable to use hasAuthority()
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
        // Allow CORS for jwts and allow everybody to access the login endpoint
            .csrf(csrf -> csrf.disable())
            //Permit only if authenticated
            .authorizeHttpRequests(auth -> {
                auth.requestMatchers("/api/register/**", "/index.html").permitAll();
                auth.requestMatchers("/api/products/**", "/api/users/**", "/api/swagger-ui/**").hasAuthority("ROLE_ADMIN");
                auth.anyRequest().authenticated();
            })
            .httpBasic(Customizer.withDefaults()
            )
            .build();
    }

}
