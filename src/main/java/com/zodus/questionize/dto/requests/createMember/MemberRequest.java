package com.zodus.questionize.dto.requests.createMember;

import jakarta.annotation.Nullable;

import java.util.UUID;

public record MemberRequest(
    @Nullable String name,
    @Nullable UUID departmentId
) {
}
