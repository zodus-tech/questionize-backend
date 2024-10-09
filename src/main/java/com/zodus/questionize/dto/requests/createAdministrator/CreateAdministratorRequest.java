package com.zodus.questionize.dto.requests.createAdministrator;

import java.util.UUID;

public record CreateAdministratorRequest(
    String name,
    String username,
    String password,
    UUID departmentId
) {
}
