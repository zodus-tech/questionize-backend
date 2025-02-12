package com.zodus.questionize.dto.filters;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

public record StatisticsFilter(
    Period period,
    LocalDateTime from,
    LocalDateTime to,
    UUID questionaryId,
    UUID departmentId
) {
}
