package com.zodus.questionize.dto;

import com.zodus.questionize.enums.QuestionType;

import java.util.UUID;

public record QuestionDTO(
    UUID id,
    String text,
    QuestionType type,
    StatisticsDTO statistics
) {
}
