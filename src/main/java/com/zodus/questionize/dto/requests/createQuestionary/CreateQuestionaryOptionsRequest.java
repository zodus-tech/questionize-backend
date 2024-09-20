package com.zodus.questionize.dto.requests.createQuestionary;

import java.time.LocalDateTime;

public record CreateQuestionaryOptionsRequest(
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer answersLimit,
    Boolean anonymous
) {
}