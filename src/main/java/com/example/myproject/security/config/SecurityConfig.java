package com.example.myproject.security.config;

import com.example.myproject.security.auth.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()  // Habilita CORS
                .csrf().disable()  // Deshabilita CSRF (si lo deseas)
                .authorizeRequests()
                .antMatchers("/api/v1/token").permitAll()  // Permite el acceso a /api/v1/token sin autenticación
                .anyRequest().authenticated()  // Requiere autenticación para otras rutas
                .and()
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)  // Agrega el filtro de JWT
                .formLogin().disable()
                .httpBasic().disable();
    }




}
