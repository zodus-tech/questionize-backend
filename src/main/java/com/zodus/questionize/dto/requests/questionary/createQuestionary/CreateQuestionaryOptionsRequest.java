package com.zodus.questionize.dto.requests.questionary.createQuestionary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateQuestionaryOptionsRequest(
    LocalDateTime startDate,
    LocalDateTime endDate,
    Integer answersLimit,
    Boolean anonymous,
    List<UUID> membersIds
) {
}