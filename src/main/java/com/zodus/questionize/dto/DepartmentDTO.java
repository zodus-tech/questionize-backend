package com.zodus.questionize.dto;

import java.util.List;
import java.util.UUID;

public record DepartmentDTO(
    UUID id,
    String name,
    List<MemberDTO> members
) {
}
