package com.zodus.questionize.dto.specifications;

import com.zodus.questionize.models.questions.QuestionType;

import java.util.UUID;

public record AnswerSpecificationDTO(
    QuestionType type,
    UUID questionaryId,
    UUID departmentId
) {
}
