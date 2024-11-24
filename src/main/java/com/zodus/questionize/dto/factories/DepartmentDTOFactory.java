package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.DepartmentDTO;
import com.zodus.questionize.dto.MemberDTO;
import com.zodus.questionize.models.Department;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class DepartmentDTOFactory {
  private final MemberDTOFactory memberDTOFactory;

  public DepartmentDTO create(Department department) {
    List<MemberDTO> members = department.getMembers().stream().map(memberDTOFactory::create).toList();

    return new DepartmentDTO(department.getId(), department.getName(), members);
  }

  public List<DepartmentDTO> create(List<Department> departments) {
    return departments.stream().map(this::create).toList();
  }
}
