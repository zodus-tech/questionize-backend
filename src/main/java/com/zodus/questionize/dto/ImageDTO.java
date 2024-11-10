package com.zodus.questionize.dto;

import jakarta.annotation.Nullable;

import java.util.UUID;

public record ImageDTO(
    UUID id,
    String name,
    byte[] imageBytes,
    @Nullable
    UUID memberId,
    @Nullable
    UUID departmentId
) {
}
