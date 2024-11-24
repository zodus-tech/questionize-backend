package com.zodus.questionize.services;

import com.zodus.questionize.dto.requests.createDepartment.DepartmentRequest;
import com.zodus.questionize.models.Administrator;
import com.zodus.questionize.models.Department;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.repositories.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DepartmentService {
  private final DepartmentRepository departmentRepository;

  public Department createDepartment(DepartmentRequest request) {
    Department department = new Department(request.name());

    return departmentRepository.save(department);
  }

  public Department updateDepartment(DepartmentRequest request, UUID id) throws ResponseStatusException {
    Department department = departmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if (request.name() != null) department.setName(request.name());

    return departmentRepository.save(department);
  }

  public Page<Department> findAllDepartments(Pageable pageable) {
    return departmentRepository.findAll(pageable);
  }

  public Department findById(UUID id) throws ResponseStatusException {
    return departmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Department findByName(String name) throws ResponseStatusException {
    return departmentRepository.findByName(name).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public void deleteDepartment(UUID id) throws ResponseStatusException {
    Department department = departmentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    departmentRepository.delete(department);
  }
}
