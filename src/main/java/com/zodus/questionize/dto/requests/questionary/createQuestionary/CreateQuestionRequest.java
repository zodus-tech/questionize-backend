package com.zodus.questionize.dto.requests.questionary.createQuestionary;

import com.zodus.questionize.models.questions.QuestionType;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

public record CreateQuestionRequest(
    String text,
    QuestionType type,
    @Nullable List<String> options,
    @Nullable UUID memberId
    ) {
}