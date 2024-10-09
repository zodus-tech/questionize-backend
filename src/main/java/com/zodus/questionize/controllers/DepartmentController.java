package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.DepartmentDTO;
import com.zodus.questionize.dto.factories.DepartmentDTOFactory;
import com.zodus.questionize.dto.requests.createDepartment.CreateDepartmentRequest;
import com.zodus.questionize.models.Department;
import com.zodus.questionize.services.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
public class DepartmentController {
  private final DepartmentService departmentService;

  @PostMapping("/create")
  public ResponseEntity<DepartmentDTO> createDepartment(CreateDepartmentRequest request) {
    Department department = departmentService.createDepartment(request);
    DepartmentDTO response = DepartmentDTOFactory.create(department);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/all")
  public ResponseEntity<PagedModel<DepartmentDTO>> findAllDepartments(Pageable pageable) {
    Page<Department> departmentsPage = departmentService.findAllDepartments(pageable);

    List<DepartmentDTO> departmentDTOList = DepartmentDTOFactory.create(departmentsPage.getContent());
    Page<DepartmentDTO> departmentsDTOPage = new PageImpl<>(departmentDTOList, pageable, departmentsPage.getTotalElements());
    PagedModel<DepartmentDTO> response = new PagedModel<>(departmentsDTOPage);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<DepartmentDTO> findDepartmentById(@PathVariable UUID id) {
    Department department = departmentService.findById(id);
    DepartmentDTO response = DepartmentDTOFactory.create(department);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<DepartmentDTO> findDepartmentByName(@PathVariable String name) {
    Department department = departmentService.findByName(name);
    DepartmentDTO response = DepartmentDTOFactory.create(department);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
