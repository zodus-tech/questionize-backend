package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.DepartmentDTO;
import com.zodus.questionize.dto.factories.DepartmentDTOFactory;
import com.zodus.questionize.dto.requests.createdepartment.DepartmentRequest;
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
  private final DepartmentDTOFactory departmentDTOFactory;

  @PostMapping("/create")
  public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentRequest request) {
    Department department = departmentService.createDepartment(request);
    DepartmentDTO response = departmentDTOFactory.create(department);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody DepartmentRequest request, @PathVariable UUID id) {
    Department department = departmentService.updateDepartment(request, id);
    DepartmentDTO response = departmentDTOFactory.create(department);

    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteDepartment(@PathVariable UUID id) {
    departmentService.deleteDepartment(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @GetMapping("/all")
  public ResponseEntity<PagedModel<DepartmentDTO>> findAllDepartments(Pageable pageable) {
    Page<Department> departmentsPage = departmentService.findAllDepartments(pageable);

    List<DepartmentDTO> departmentDTOList = departmentDTOFactory.create(departmentsPage.getContent());
    Page<DepartmentDTO> departmentsDTOPage = new PageImpl<>(departmentDTOList, pageable, departmentsPage.getTotalElements());
    PagedModel<DepartmentDTO> response = new PagedModel<>(departmentsDTOPage);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/id/{id}")
  public ResponseEntity<DepartmentDTO> findDepartmentById(@PathVariable UUID id) {
    Department department = departmentService.findById(id);
    DepartmentDTO response = departmentDTOFactory.create(department);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/name/{name}")
  public ResponseEntity<DepartmentDTO> findDepartmentByName(@PathVariable String name) {
    Department department = departmentService.findByName(name);
    DepartmentDTO response = departmentDTOFactory.create(department);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
