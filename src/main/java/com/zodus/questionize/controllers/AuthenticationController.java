package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.AdministratorDTO;
import com.zodus.questionize.dto.AuthenticationDTO;
import com.zodus.questionize.dto.factories.AdministratorDTOFactory;
import com.zodus.questionize.dto.requests.authentication.AuthenticationRequest;
import com.zodus.questionize.dto.requests.createAdministrator.CreateAdministratorRequest;
import com.zodus.questionize.models.Administrator;
import com.zodus.questionize.services.AdministratorService;
import com.zodus.questionize.services.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {
  private final AuthenticationService authenticationService;
  private final AdministratorService administratorService;

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
}
