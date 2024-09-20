package com.zodus.questionize.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record QuestionaryOptionsDTO(
    UUID id,
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer answersLimit,
    Boolean anonymous
) {
}
