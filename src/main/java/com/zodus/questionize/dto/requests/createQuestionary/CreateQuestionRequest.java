package com.zodus.questionize.dto.requests.createQuestionary;

import com.zodus.questionize.enums.QuestionType;

public record CreateQuestionRequest(
    String text,
    QuestionType type
) {
}