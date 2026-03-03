package com.sprint3.admission_test.infrastructure.adapter.in.security;

import com.sprint3.admission_test.application.ports.in.IAuthorizationUseCase;
import com.sprint3.admission_test.domain.ports.out.UserDetailsImpl;
import com.sprint3.admission_test.infrastructure.filter.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;
import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final User DEFAULT_USER = new User(
            "Admin",
            "Password",
            List.of("ROLE_ADMIN")
    );


    @Bean
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(r -> r
                        .requestMatchers("/*").permitAll()
                        .anyRequest().permitAll()
                )
                .build();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return (username) -> {
            return Optional.ofNullable(username)
                    .filter(n -> DEFAULT_USER.getUser().equals(n))
                    .map(name -> UserDetailsImpl.of(DEFAULT_USER))
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        };
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


    @Data
    @AllArgsConstructor
    public static class User {
        private String user;
        private String password;
        private List<String> roles;
    }
}
