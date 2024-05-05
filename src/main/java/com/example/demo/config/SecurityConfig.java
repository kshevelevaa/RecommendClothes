package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    private static final String[] WHITE_LIST = new String[]{
            "login",
            "recommend",
            "category"

    };
    private final JwtFilter jwtFilter;

    public SecurityConfig(
            JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain doSecurityFilter(HttpSecurity http) throws Exception {
//        http.sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .dispatcherTypeMatchers()
//                .permitAll()
//                .dispatcherTypeMatchers()
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .disable()
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        http.csrf()
                .disable()
                .cors().disable();
//                .and()
//                .authorizeHttpRequests()
//                .requestMatchers("*")
//                .permitAll();

        return http.build();
    }
}
