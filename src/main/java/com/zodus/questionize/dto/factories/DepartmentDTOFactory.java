package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.DepartmentDTO;
import com.zodus.questionize.models.Department;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DepartmentDTOFactory {
  public DepartmentDTO create(Department department) {
    return new DepartmentDTO(department.getId(), department.getName());
  }

  public List<DepartmentDTO> create(List<Department> departments) {
    return departments.stream().map(this::create).toList();
  }
}
