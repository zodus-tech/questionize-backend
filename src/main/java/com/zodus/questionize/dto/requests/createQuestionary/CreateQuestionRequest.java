package com.zodus.questionize.dto.requests.createQuestionary;

import com.zodus.questionize.enums.QuestionType;
import jakarta.annotation.Nullable;

import java.util.List;

public record CreateQuestionRequest(
    String text,
    QuestionType type,
    @Nullable List<String> options
    ) {
}