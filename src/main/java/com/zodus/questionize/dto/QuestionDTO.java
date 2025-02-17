package com.zodus.questionize.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zodus.questionize.models.questions.QuestionType;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record QuestionDTO(
    UUID id,
    QuestionType type,
    String text,
    @Nullable List<String> options
) {
  public QuestionDTO(UUID id, QuestionType type, String text) {
    this(id, type, text, null);
  }
}
