package me.brennorodrigues.gestao_vagas.security;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.brennorodrigues.gestao_vagas.providers.JWTProvider;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JWTProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
            SecurityContextHolder.getContext().setAuthentication(null);

            String header = request.getHeader("Authorization");

            if(header != null) {
                var subjectToken = this.jwtProvider.validateToken(header);

                if(subjectToken.isEmpty()) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }

                request.setAttribute("company_id", subjectToken);
                UsernamePasswordAuthenticationToken auth = 
                new UsernamePasswordAuthenticationToken(subjectToken, null  /*credentials*/, Collections.emptyList() /*ROLES*/);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }   
            filterChain.doFilter(request, response);
                
        }
    
}
