package com.zodus.questionize.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zodus.questionize.models.questions.types.enums.Rating;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record StatisticsDTO(
    Long totalQuestionnairesActive,
    Long totalSubmissions,
    Map<String, Long> totalSubmissionsPerPeriod,
    Map<Rating, Long> satisfactionDistribution,
    Long unfinishedSubmissions
) {
}
