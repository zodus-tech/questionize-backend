package com.zodus.questionize.dto.requests.createdepartment;

import jakarta.annotation.Nullable;

public record DepartmentRequest(
    @Nullable String name
) {
}
