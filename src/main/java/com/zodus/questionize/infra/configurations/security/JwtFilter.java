package com.zodus.questionize.infra.configurations.security;

import com.zodus.questionize.models.Administrator;
import com.zodus.questionize.repositories.AdministratorRepository;
import com.zodus.questionize.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
  private final TokenService tokenService;
  private final AdministratorRepository administratorRepository;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    var token = this.recoverToken(request);
    if (token != null) {
      String subject = tokenService.extractSubject(token);
      Administrator administrator = administratorRepository.findByUsername(subject).orElseThrow();
      Collection<? extends GrantedAuthority> authorities = administrator.getAuthorities();

      assert tokenService.isTokenNotExpired(token) : "Token expirado, faça login novamente";
      var authentication = new UsernamePasswordAuthenticationToken(administrator, null, authorities);
      SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    filterChain.doFilter(request, response);
  }

  private String recoverToken(HttpServletRequest request) {
    var authHeader = request.getHeader("Authorization");
    if (authHeader == null) {
      return null;
    }
    return authHeader.replace("Bearer ", "");
  }
}
