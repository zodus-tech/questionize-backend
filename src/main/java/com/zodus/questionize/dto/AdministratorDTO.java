package com.zodus.questionize.dto;

import java.util.UUID;

public record AdministratorDTO(
    UUID id,
    String name,
    String username,
    DepartmentDTO department
) {
}
