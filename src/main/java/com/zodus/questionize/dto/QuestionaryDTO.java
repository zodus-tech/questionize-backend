package com.zodus.questionize.dto;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record QuestionaryDTO(
    UUID id,
    String title,
    LocalDateTime createdAt,
    QuestionaryOptionsDTO options,
    Set<QuestionDTO> questions
) {
}
