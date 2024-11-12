package com.zodus.questionize.dto.requests.createMember;

import java.util.UUID;

public record CreateMemberRequest(
    String name,
    UUID departmentId
) {
}
