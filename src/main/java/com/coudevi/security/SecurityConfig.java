package com.coudevi.security;

import com.coudevi.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final CustomUserDetailService userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                // públicos
                                .requestMatchers("/api/auth/**").permitAll() // login, registro, etc
                        // solo para el cliente
                                .requestMatchers("/api/cliente/**").hasRole("CLIENTE")
                        // solo para los trabajadores
                                .requestMatchers("/api/trabajador/**").hasRole("TRABAJADOR")
                        // solo para recepcionistas
                                .requestMatchers("/api/recepcionista/**").hasRole("RECEPCIONISTA")
                                // SOlo para administradores
                                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/users").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH,  "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,    "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.POST, "/api/clientes").hasAnyRole("ADMIN", "RECEPCIONISTA")
                                .requestMatchers(HttpMethod.POST, "/api/orders").hasAnyRole("ADMIN", "RECEPCIONISTA")
                                .requestMatchers(HttpMethod.GET,  "/api/orders/available").hasRole("TRABAJADOR")
                                .requestMatchers(HttpMethod.POST, "/api/orders/*/take").hasRole("TRABAJADOR")
                                .requestMatchers(HttpMethod.GET,  "/api/orders/my/active").hasRole("TRABAJADOR")
                                .requestMatchers(HttpMethod.PATCH, "/api/orders/*/assign").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH, "/api/orders/*/complete").hasRole("TRABAJADOR")
                                .requestMatchers(HttpMethod.PATCH, "/api/orders/*/deliver").hasAnyRole("RECEPCIONISTA", "ADMIN")
                                .requestMatchers(HttpMethod.POST,   "/api/service-types").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT,    "/api/service-types/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PATCH,  "/api/service-types/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET,    "/api/service-types/**").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/client/orders").hasRole("CLIENTE")
                                // lo demás requiere autenticación
                                .anyRequest().authenticated()
                )
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
