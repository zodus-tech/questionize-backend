package com.zodus.questionize.dto;

import java.time.LocalDateTime;

public record QuestionaryOptionsDTO(
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer answersLimit,
    Boolean anonymous
) {
}
