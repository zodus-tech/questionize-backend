package com.zodus.questionize.dto.requests.createadministrator;

import java.util.UUID;

public record CreateAdministratorRequest(
    String name,
    String username,
    String password,
    UUID departmentId
) {
}
