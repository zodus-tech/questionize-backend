package com.zodus.questionize.services;

import com.zodus.questionize.dto.GeneralStatisticsDTO;
import com.zodus.questionize.dto.filters.GeneralStatisticsFilter;
import com.zodus.questionize.models.questions.QuestionType;
import com.zodus.questionize.models.questions.types.enums.Rating;
import com.zodus.questionize.repositories.AnswerRepository;
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
  private final AnswerRepository answerRepository;
  private final SubmissionTokenService submissionTokenService;

  public GeneralStatisticsDTO getGeneralStatistics(GeneralStatisticsFilter filter) {
    Period period = filter.period();
    LocalDate from = filter.from();
    LocalDate to = filter.to();
    LocalDate now = LocalDate.now();

    long totalQuestionnairesActive = questionaryRepository.countByStartDateBeforeAndEndDateAfter(now.atStartOfDay(), now.atStartOfDay());
    long totalSubmissions = submissionRepository.countBySubmittedAtBetween(from.atStartOfDay(), to.atStartOfDay());
    Map<String, Long> statisticsPerPeriod = getStatisticsPerPeriod(period, from, to);
    Map<Rating, Long> satisfactionDistribution = getSatisfactionDistribution();
    long unfinishedSubmissions = submissionTokenService.countTotal();

    return new GeneralStatisticsDTO(
        totalQuestionnairesActive,
        totalSubmissions,
        statisticsPerPeriod,
        satisfactionDistribution,
        unfinishedSubmissions
    );
  }

  private Map<Rating, Long> getSatisfactionDistribution() {
    long veryDissatisfied = answerRepository.countAllByQuestionTypeAndAnswer(QuestionType.RATING, Rating.VERY_DISSATISFIED.toString());
    long dissatisfied = answerRepository.countAllByQuestionTypeAndAnswer(QuestionType.RATING, Rating.DISSATISFIED.toString());
    long neutral = answerRepository.countAllByQuestionTypeAndAnswer(QuestionType.RATING, Rating.NEUTRAL.toString());
    long satisfactory = answerRepository.countAllByQuestionTypeAndAnswer(QuestionType.RATING, Rating.SATISFACTORY.toString());
    long verySatisfactory = answerRepository.countAllByQuestionTypeAndAnswer(QuestionType.RATING, Rating.VERY_SATISFACTORY.toString());

    Map<Rating, Long> response = new TreeMap<>();
    response.put(Rating.VERY_DISSATISFIED, veryDissatisfied);
    response.put(Rating.DISSATISFIED, dissatisfied);
    response.put(Rating.NEUTRAL, neutral);
    response.put(Rating.SATISFACTORY, satisfactory);
    response.put(Rating.VERY_SATISFACTORY, verySatisfactory);

    return response;
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
