package me.brennorodrigues.gestao_vagas.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.brennorodrigues.gestao_vagas.providers.JWTCompanyProvider;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private JWTCompanyProvider jwtCompanyProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
            
            String header = request.getHeader("Authorization");

            if(request.getRequestURI().startsWith("/company")  && header != null){
                var token = this.jwtCompanyProvider.validateToken(header);

                if(token == null) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    return;
                }
                
                var roles = token.getClaim("roles").asList(Object.class);
                var grants = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.toString().toUpperCase())).toList();
                
                request.setAttribute("company_id", token.getSubject());

                UsernamePasswordAuthenticationToken auth = 
                new UsernamePasswordAuthenticationToken(token.getSubject(), null  /*credentials*/, grants /*ROLES*/);
                SecurityContextHolder.getContext().setAuthentication(auth);
                    
            }
            
             
            filterChain.doFilter(request, response);
                
        }
    
}
