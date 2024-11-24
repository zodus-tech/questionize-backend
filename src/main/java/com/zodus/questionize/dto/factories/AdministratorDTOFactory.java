package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.AdministratorDTO;
import com.zodus.questionize.dto.DepartmentDTO;
import com.zodus.questionize.models.Administrator;
import com.zodus.questionize.models.Department;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdministratorDTOFactory {
  private final DepartmentDTOFactory departmentDTOFactory;

  public AdministratorDTO create(Administrator administrator) {
    Department department = administrator.getDepartment();
    DepartmentDTO departmentDTO = departmentDTOFactory.create(department);
    return new AdministratorDTO(
        administrator.getId(),
        administrator.getName(),
        administrator.getUsername(),
        departmentDTO
    );
  }
}
