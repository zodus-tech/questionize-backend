package com.zodus.questionize.dto;

import com.zodus.questionize.enums.QuestionType;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

public record QuestionDTO(
    UUID id,
    String text,
    QuestionType type,
    StatisticsDTO statistics,
    @Nullable List<String> options
) {
}
