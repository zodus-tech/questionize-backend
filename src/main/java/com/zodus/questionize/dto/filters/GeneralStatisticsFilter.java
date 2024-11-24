package com.zodus.questionize.dto.filters;

import java.time.LocalDate;
import java.time.Period;

public record GeneralStatisticsFilter(
    Period period,
    LocalDate from,
    LocalDate to
) {
}
