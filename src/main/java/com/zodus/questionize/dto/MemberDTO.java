package com.zodus.questionize.dto;

import jakarta.annotation.Nullable;

import java.util.UUID;

public record MemberDTO(
    UUID id,
    String name,
    UUID departmentId,
    @Nullable
    UUID pictureId
) {
}
