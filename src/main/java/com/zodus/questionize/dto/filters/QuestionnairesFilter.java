package com.zodus.questionize.dto.filters;

import java.util.UUID;

public record QuestionnairesFilter(
    UUID departmentId,
    String title,
    Boolean active
) {
}
