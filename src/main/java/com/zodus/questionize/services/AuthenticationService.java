package com.zodus.questionize.services;

import com.zodus.questionize.dto.requests.authentication.AuthenticationRequest;
import com.zodus.questionize.models.Administrator;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthenticationService {
  private final AuthenticationManager authenticationManager;
  private final TokenService tokenService;

  public String login(AuthenticationRequest request) {
    var usernamePassword = new UsernamePasswordAuthenticationToken(request.username(), request.password());
    var auth = this.authenticationManager.authenticate(usernamePassword);
    return tokenService.generateToken((Administrator) auth.getPrincipal(), request.rememberUser());
  }
}
