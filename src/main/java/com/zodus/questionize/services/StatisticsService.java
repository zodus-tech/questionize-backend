package com.zodus.questionize.services;

import com.zodus.questionize.dto.StatisticsDTO;
import com.zodus.questionize.dto.filters.StatisticsFilter;
import com.zodus.questionize.models.Submission;
import com.zodus.questionize.models.questions.types.enums.Rating;
import com.zodus.questionize.repositories.SubmissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

@Service
@AllArgsConstructor
public class StatisticsService {
  private final QuestionaryService questionaryService;
  private final SubmissionService submissionService;
  private final AnswerService answerService;
  private final SubmissionTokenService submissionTokenService;
  private final SubmissionRepository submissionRepository;

  public StatisticsDTO getStatistics(StatisticsFilter filter) {
    LocalDateTime now = LocalDateTime.now();

    Specification<Submission> specification = submissionService.createSpecification(filter);
    long totalSubmissions = submissionRepository.count(specification);
    long totalQuestionnairesActive = questionaryService.countAllBetween(now, now);
    Map<String, Long> statisticsPerPeriod = getStatisticsPerPeriod(filter);
    long unfinishedSubmissions = submissionTokenService.countTotal();
    Map<Rating, Long> satisfactionDistribution = getSatisfactionDistribution(filter);

    return new StatisticsDTO(
        totalQuestionnairesActive,
        totalSubmissions,
        statisticsPerPeriod,
        satisfactionDistribution,
        unfinishedSubmissions
    );
  }

  private Map<Rating, Long> getSatisfactionDistribution(StatisticsFilter filter) {
   return answerService.countAllRatingQuestions(filter);
  }

  private Map<String, Long> getStatisticsPerPeriod(StatisticsFilter filter) {
    Period period = filter.period();
    LocalDateTime from = filter.from();
    LocalDateTime to = filter.to();

    int days = period.getDays();
    int months = period.getMonths();
    int years = period.getYears();
    Map<String, Long> response = new TreeMap<>();

    LocalDateTime aux = from
        .plusDays(days)
        .plusMonths(months)
        .plusYears(years);

    Specification<Submission> specification = submissionService.createSpecification(filter);

    while (aux.isBefore(to) || aux.isEqual(to)) {
      long submissionsInPeriod = submissionRepository.count(specification);
      response.put(from + " - " + aux, submissionsInPeriod);

      from = aux.plusDays(1);
      aux = from
          .plusDays(days)
          .plusMonths(months)
          .plusYears(years);

      filter = new StatisticsFilter(filter.period(), from, to, filter.questionaryId(), filter.departmentId());
      specification = submissionService.createSpecification(filter);
    }
    long submissionsInPeriod = submissionRepository.count(specification);
    response.put(from + " - " + aux, submissionsInPeriod);

    return response;
  }
}
