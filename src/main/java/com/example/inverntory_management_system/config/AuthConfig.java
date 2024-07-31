package com.example.inverntory_management_system.config;

import com.example.inverntory_management_system.security.UserAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthConfig {

  @Autowired
  private UserAuthProvider authProvider;

  @Bean
  public AuthenticationManager authManager(HttpSecurity http) throws Exception {
    AuthenticationManagerBuilder authenticationManagerBuilder =
      http.getSharedObject(AuthenticationManagerBuilder.class);
    authenticationManagerBuilder.authenticationProvider(authProvider);
    return authenticationManagerBuilder.build();
  }

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.
      csrf((c) -> c.disable())
      .authorizeHttpRequests((auth) ->
        auth
          .requestMatchers("/auth/login","/auth/register","/assets/**").permitAll()
          .anyRequest().authenticated()).
      formLogin((form) ->
        form
          .loginPage("/auth/login")
          .defaultSuccessUrl("/")
          .failureUrl("/auth/login?error=true")
          .permitAll())
      .logout((logout) ->
        logout
          .logoutSuccessUrl("/auth/login?logout=true")
          .invalidateHttpSession(true)
          .clearAuthentication(true)
          .permitAll())
      .build();

  }


}
