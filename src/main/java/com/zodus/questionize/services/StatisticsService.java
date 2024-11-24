package com.zodus.questionize.services;

import com.zodus.questionize.dto.GeneralStatisticsDTO;
import com.zodus.questionize.dto.filters.GeneralStatisticsFilter;
import com.zodus.questionize.repositories.QuestionaryRepository;
import com.zodus.questionize.repositories.SubmissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.TreeMap;

@Service
@AllArgsConstructor
public class StatisticsService {
  private final QuestionaryRepository questionaryRepository;
  private final SubmissionRepository submissionRepository;

  public GeneralStatisticsDTO getGeneralStatistics(GeneralStatisticsFilter filter) {
    Period period = filter.period();
    LocalDate from = filter.from();
    LocalDate to = filter.to();
    LocalDate now = LocalDate.now();

    long totalQuestionnairesActive = questionaryRepository.countByStartDateBeforeAndEndDateAfter(now.atStartOfDay(), now.atStartOfDay());
    long totalSubmissions = submissionRepository.countBySubmittedAtBetween(from.atStartOfDay(), to.atStartOfDay());
    Map<String, Long> statisticsPerPeriod = getStatisticsPerPeriod(period, from, to);

    return new GeneralStatisticsDTO(
        totalQuestionnairesActive,
        totalSubmissions,
        statisticsPerPeriod
    );
  }

  private Map<String, Long> getStatisticsPerPeriod(Period period, LocalDate from, LocalDate to) {
    int days = period.getDays();
    int months = period.getMonths();
    int years = period.getYears();
    Map<String, Long> response = new TreeMap<>();

    LocalDate aux = from
        .plusDays(days)
        .plusMonths(months)
        .plusYears(years);

    while (aux.isBefore(to) || aux.isEqual(to)) {
      long submissionsInPeriod = submissionRepository.countBySubmittedAtBetween(from.atStartOfDay(), aux.atStartOfDay());
      response.put(from + " - " + aux, submissionsInPeriod);

      from = aux.plusDays(1);
      aux = from
          .plusDays(days)
          .plusMonths(months)
          .plusYears(years);
    }
    long submissionsInPeriod = submissionRepository.countBySubmittedAtBetween(from.atStartOfDay(), aux.atStartOfDay());
    response.put(from + " - " + aux, submissionsInPeriod);

    return response;
  }
}
