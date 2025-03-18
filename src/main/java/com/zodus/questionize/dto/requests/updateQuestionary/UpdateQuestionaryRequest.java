package com.zodus.questionize.dto.requests.updateQuestionary;

import java.time.LocalDateTime;
import java.util.Optional;

public record UpdateQuestionaryRequest(
    Optional<String> title,
    Optional<LocalDateTime> startDate,
    Optional<LocalDateTime> endDate
) {
}
