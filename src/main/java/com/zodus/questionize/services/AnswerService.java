package com.zodus.questionize.services;

import com.zodus.questionize.models.questions.QuestionType;
import com.zodus.questionize.models.questions.types.enums.Rating;
import com.zodus.questionize.repositories.AnswerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.TreeMap;

@Service
@AllArgsConstructor
public class AnswerService {
  private final AnswerRepository answerRepository;

  public Map<Rating, Long> countAllRatingQuestions() {
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
}
