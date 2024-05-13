package com.example.blog.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        PasswordEncoder passwordEncoder = passwordEncoder();

        UserDetails jhon = User.builder()
                .username("jhon")
                .password(passwordEncoder.encode("test123"))
                .roles("USER")
                .build();

        UserDetails smith = User.builder()
                .username("smith")
                .password(passwordEncoder.encode("test123"))
                .roles("USER", "ADMIN")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password(passwordEncoder.encode("test123"))
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(jhon, smith, mary);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception {
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET,"/api/blogPost").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/api/blogPost/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/blogPost").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT,"/api/blogPost/{id}").hasRole("USER")
                        .requestMatchers(HttpMethod.DELETE,"/api/blogPost/{id}").hasRole("ADMIN")
        );
        http.httpBasic(Customizer.withDefaults());

        http.csrf(csrf->csrf.disable());
        return http.build();
    }
}
