package com.zodus.questionize.dto.requests.questionary.createQuestionary;

import java.time.LocalDateTime;

public record CreateQuestionaryOptionsRequest(
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer answersLimit,
    Boolean anonymous
) {
}