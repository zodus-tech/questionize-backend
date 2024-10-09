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
  public static AdministratorDTO create(Administrator administrator) {
    Department department = administrator.getDepartment();
    DepartmentDTO departmentDTO = new DepartmentDTO(department.getId(), department.getName());
    return new AdministratorDTO(
        administrator.getId(),
        administrator.getName(),
        administrator.getUsername(),
        departmentDTO
    );
  }
}
