package com.zodus.questionize.services;

import com.zodus.questionize.models.Administrator;
import com.zodus.questionize.repositories.AdministratorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AdministratorService implements UserDetailsService {
  private final AdministratorRepository administratorRepository;

  @Override
  public Administrator loadUserByUsername(String username) throws UsernameNotFoundException, ResponseStatusException {
    return administratorRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
}
