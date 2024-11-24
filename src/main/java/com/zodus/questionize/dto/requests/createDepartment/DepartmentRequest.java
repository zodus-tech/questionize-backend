package com.zodus.questionize.dto.requests.createDepartment;

import jakarta.annotation.Nullable;

public record DepartmentRequest(
    @Nullable String name
) {
}
