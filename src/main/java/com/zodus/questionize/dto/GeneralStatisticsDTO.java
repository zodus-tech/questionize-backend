package com.zodus.questionize.dto;

import com.zodus.questionize.models.questions.types.enums.Rating;

import java.util.Map;

public record GeneralStatisticsDTO(
    long totalQuestionnairesActive,
    long totalSubmissions,
    Map<String, Long> totalSubmissionsPerPeriod,
    Map<Rating, Long> satisfactionDistribution
) {
}
