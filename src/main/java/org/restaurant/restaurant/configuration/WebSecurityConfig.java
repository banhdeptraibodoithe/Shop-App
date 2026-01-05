package org.restaurant.restaurant.configuration;

import lombok.RequiredArgsConstructor;
import org.restaurant.restaurant.filters.JwtTokenFilter;
import org.restaurant.restaurant.models.Role;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    @Value("${api.prefix}")
    private String apiPrefix;
    private final JwtTokenFilter jwtTokenFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) {
         httpSecurity.csrf(AbstractHttpConfigurer::disable)
                 .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(request-> request
                        .requestMatchers(String.format("/%s/users/register", apiPrefix),
                                        String.format("/%s/users/login", apiPrefix)
                        )
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, String.format("/%s/categories/**", apiPrefix))
                        .hasAnyRole(Role.USER, Role.ADMIN)
                        .requestMatchers(HttpMethod.POST, String.format("/%s/categories/**", apiPrefix))
                        .hasRole(Role.ADMIN)
                        .requestMatchers(HttpMethod.PATCH, String.format("/%s/categories/**", apiPrefix))
                        .hasRole(Role.ADMIN)
                        .requestMatchers(HttpMethod.DELETE, String.format("/%s/categories/**", apiPrefix))
                        .hasRole(Role.ADMIN)
                        .requestMatchers(HttpMethod.GET, String.format("/%s/products/**", apiPrefix))
                        .hasAnyRole(Role.USER, Role.ADMIN)
                        .requestMatchers(HttpMethod.POST, String.format("/%s/products/**", apiPrefix))
                        .hasRole(Role.ADMIN)
                        .requestMatchers(HttpMethod.PATCH, String.format("/%s/products/**", apiPrefix))
                        .hasRole(Role.ADMIN)
                        .requestMatchers(HttpMethod.DELETE, String.format("/%s/products/**", apiPrefix))
                        .hasRole(Role.ADMIN)
                        .requestMatchers(HttpMethod.POST, String.format("/%s/orders/**", apiPrefix))
                        .hasAnyRole(Role.USER)
                        .requestMatchers(HttpMethod.GET, String.format("/%s/orders/**", apiPrefix))
                        .hasAnyRole(Role.USER, Role.ADMIN)
                        .requestMatchers(HttpMethod.PUT, String.format("/%s/orders/**", apiPrefix))
                        .hasRole(Role.ADMIN)
                        .requestMatchers(HttpMethod.DELETE, String.format("/%s/orders/**", apiPrefix))
                        .hasRole(Role.ADMIN)
                        .requestMatchers(HttpMethod.POST, String.format("/%s/order-details/**", apiPrefix))
                        .hasAnyRole(Role.USER)
                        .requestMatchers(HttpMethod.GET, String.format("/%s/order-details/**", apiPrefix))
                        .hasAnyRole(Role.USER, Role.ADMIN)
                        .requestMatchers(HttpMethod.PUT, String.format("/%s/order-details/**", apiPrefix))
                        .hasRole(Role.ADMIN)
                        .requestMatchers(HttpMethod.DELETE, String.format("/%s/order-details/**", apiPrefix))
                        .hasRole(Role.ADMIN)
                        .anyRequest().authenticated());
         return httpSecurity.build();
    }
}
