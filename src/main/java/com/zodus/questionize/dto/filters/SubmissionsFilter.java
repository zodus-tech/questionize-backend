package com.zodus.questionize.dto.filters;

import java.time.LocalDateTime;
import java.util.UUID;

public record SubmissionsFilter(
    UUID memberId,
    LocalDateTime from,
    LocalDateTime to
) {
}
