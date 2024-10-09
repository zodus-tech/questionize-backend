package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.AdministratorDTO;
import com.zodus.questionize.dto.AuthenticationDTO;
import com.zodus.questionize.dto.factories.AdministratorDTOFactory;
import com.zodus.questionize.dto.requests.authentication.AuthenticationRequest;
import com.zodus.questionize.dto.requests.createAdministrator.CreateAdministratorRequest;
import com.zodus.questionize.models.Administrator;
import com.zodus.questionize.services.AdministratorService;
import com.zodus.questionize.services.AuthenticationService;
import com.zodus.questionize.services.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;
  private final AdministratorService administratorService;
  private final TokenService tokenService;

  @PostMapping("/login")
  public ResponseEntity<AuthenticationDTO> login(@RequestBody AuthenticationRequest request) {
    String token = authenticationService.login(request);
    Administrator administrator = administratorService.loadUserByUsername(request.username());

    AuthenticationDTO response = new AuthenticationDTO(
        AdministratorDTOFactory.create(administrator),
        token
    );

    return new ResponseEntity<>(response, HttpStatus.OK);
  }


  @PostMapping("/register")
  public ResponseEntity<AdministratorDTO> register(@RequestBody CreateAdministratorRequest request) {
    Administrator administrator = administratorService.createNewUser(request);
    AdministratorDTO response = AdministratorDTOFactory.create(administrator);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/refresh")
  public ResponseEntity<AuthenticationDTO> refresh(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
    Administrator administrator = tokenService.extractUser(authHeader);
    String token = tokenService.generateToken(administrator, true);

    AuthenticationDTO response = new AuthenticationDTO(
        AdministratorDTOFactory.create(administrator),
        token
    );

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
