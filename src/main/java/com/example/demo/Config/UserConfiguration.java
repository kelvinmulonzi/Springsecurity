package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;



@Configuration

public class UserConfiguration {
    public SecurityFilterChain securityFilterChain(HttpSecurity request) throws Exception {
        request.csrf(c->c.disable())
                .authorizeHttpRequests(auth->auth
                        .requestMatchers("/api/items").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/api/").authenticated()
                        .requestMatchers("/api/items").permitAll()

                );
        return request.build();

    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    InMemoryUserDetailsManager userDetailsService() {
        var user1 = User.builder()
                .username("user")
                .password(passwordEncoder().encode("password"))
                .roles("USER")
                .build();
        var user2 = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("password"))
                .roles("ADMIN")
                .build();
 return new InMemoryUserDetailsManager(user1, user2);
    }

}
