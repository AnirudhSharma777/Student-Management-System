package com.management.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.management.Repositories.StudentRepository;

@Configuration
public class ApplicationConfig {

    private final StudentRepository studentRepository;

    public ApplicationConfig(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Bean
    UserDetailsService userDetailsService(){
        return username -> studentRepository.findByEmail(username).orElseThrow();
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

        authProvider.setUserDetailsService((userDetailsService()));
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
