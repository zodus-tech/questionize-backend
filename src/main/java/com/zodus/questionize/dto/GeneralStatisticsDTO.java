package com.zodus.questionize.dto;

import java.util.Map;

public record GeneralStatisticsDTO(
    long totalQuestionnairesActive,
    long totalSubmissions,
    Map<String, Long> totalSubmissionsPerPeriod
) {
}
