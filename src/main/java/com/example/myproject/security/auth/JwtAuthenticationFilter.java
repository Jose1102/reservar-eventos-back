package com.example.myproject.security.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    private final String miClave = "claveprueba123";

    @Override
    protected void doFilterInternal(HttpServletRequest request, javax.servlet.http.HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getRequestURI().equals("/api/v1/token") || request.getRequestURI().equals("/api/v1/addUser")) {
            filterChain.doFilter(request, response); // Continuar con el siguiente filtro si es la ruta para generar token
            return;
        }

        // Obtener el token del encabezado Authorization
        String token = request.getHeader("Authorization");

        if(token == null || !token.startsWith("Bearer ")){

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
            response.getWriter().write("No tiene autorizacion: no tiene o esta invalido el Token");
            return;
        }




        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);

            try {
                Claims claims = Jwts.parser()
                        .setSigningKey(miClave)
                        .parseClaimsJws(token)
                        .getBody();

                String username = claims.getSubject();

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (SignatureException e) {
                logger.error("Invalid JWT signature", e);
            } catch (Exception e) {
                logger.error("Failed to parse JWT", e);
            }
        }

        filterChain.doFilter(request, response);
    }


}
