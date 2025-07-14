package com.HTTTdh.project1.security;

import com.HTTTdh.project1.security.jwt.AuthEntryPointJwt;
import com.HTTTdh.project1.security.jwt.AuthTokenFilter;
import com.HTTTdh.project1.security.jwt.JwtUtils;
import com.HTTTdh.project1.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

  @Autowired
  private AuthEntryPointJwt unauthorizedHandler;

  @Autowired
  private JwtUtils jwtUtils;

  @Bean
  public AuthTokenFilter authenticationJwtTokenFilter(UserDetailsServiceImpl userDetailsService) {
    return new AuthTokenFilter(jwtUtils, userDetailsService);
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider(UserDetailsServiceImpl userDetailsService) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, UserDetailsServiceImpl userDetailsService) throws Exception {
    http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/admin/posts/**", "/admin/posts","/admin/users/**", "/admin/**", "/admin/users").hasRole("ADMIN")
                    .requestMatchers("/home", "/blog-detail","/updatePost", "/addPost", "/post/**","/post/comment","/api/images").hasRole("USER")
                    .requestMatchers(
              "/",  "/index", "/favicon.ico",
              "/css/**", "/fragments/**","/js/**",
              "/api/auth/**", "/login", "/register"
              ).permitAll()
              .anyRequest().authenticated())
        .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));

    http.authenticationProvider(authenticationProvider(userDetailsService));
    http.addFilterBefore(authenticationJwtTokenFilter(userDetailsService), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}
