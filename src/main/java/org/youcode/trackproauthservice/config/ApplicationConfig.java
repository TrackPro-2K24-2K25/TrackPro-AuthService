package org.youcode.trackproauthservice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.youcode.trackproauthservice.auditing.ApplicationAuditAware;
import org.youcode.trackproauthservice.repositories.AppUserRepository;

import java.util.UUID;

@Configuration
@RequiredArgsConstructor
@ComponentScan(basePackages = {"org.youcode.trackproauthservice"})
public class ApplicationConfig {

    private final AppUserRepository appUserRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return firstName -> appUserRepository.findByFirstName(firstName)
                .orElseThrow(()-> new UsernameNotFoundException("User with firstName " + firstName + " not found"));
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService((userDetailsService()));
        authProvider.setPasswordEncoder((passwordEncoder()));
        return authProvider;
    }


    @Bean
    public AuditorAware<UUID> auditorAware(){
        return new ApplicationAuditAware();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }




}
