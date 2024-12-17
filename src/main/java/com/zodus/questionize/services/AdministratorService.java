package com.zodus.questionize.services;

import com.zodus.questionize.dto.requests.createadministrator.CreateAdministratorRequest;
import com.zodus.questionize.models.Administrator;
import com.zodus.questionize.models.Department;
import com.zodus.questionize.repositories.AdministratorRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class AdministratorService implements UserDetailsService {
  private final AdministratorRepository administratorRepository;
  private final DepartmentService departmentService;
  private final PasswordEncoder passwordEncoder;

  @Override
  public Administrator loadUserByUsername(String username) throws UsernameNotFoundException, ResponseStatusException {
    return administratorRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Administrator createNewUser(CreateAdministratorRequest request) {
    String encryptedPassword = passwordEncoder.encode(request.password());

    Department department = departmentService.findById(request.departmentId());
    Administrator administrator = Administrator.builder()
        .name(request.name())
        .username(request.username())
        .department(department)
        .password(encryptedPassword)
        .build();

    return administratorRepository.save(administrator);
  }

  public Administrator findByUsername(String username) {
    return loadUserByUsername(username);
  }
}
