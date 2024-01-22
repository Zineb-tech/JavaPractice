package com.epi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails admin= User.withUsername("admin").password(passwordEncoder().encode("admin")).authorities("ADMIN").build();
        return new InMemoryUserDetailsManager(admin);
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(ar->
                        ar
                                .requestMatchers("/api/entreprise/add").hasAuthority("ADMIN")
                                .requestMatchers("/api/entreprise/getByVille/{ville}").hasAuthority("ADMIN")
                                .requestMatchers("/api/entreprise/findByNbEmployee/{min}/{max}").hasAuthority("ADMIN")
                                .requestMatchers("/api/entreprise/find/{id}").hasAuthority("ADMIN")
                                .requestMatchers("/api/entreprise/all").permitAll().anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
