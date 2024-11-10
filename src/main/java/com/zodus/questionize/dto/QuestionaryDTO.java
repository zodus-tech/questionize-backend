package com.zodus.questionize.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record QuestionaryDTO(
    UUID id,
    String title,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt,
    QuestionaryOptionsDTO options,
    Set<QuestionDTO> questions
) {
}
