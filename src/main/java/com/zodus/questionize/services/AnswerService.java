package com.zodus.questionize.services;

import com.zodus.questionize.dto.filters.StatisticsFilter;
import com.zodus.questionize.dto.specifications.AnswerSpecificationDTO;
import com.zodus.questionize.models.Answer;
import com.zodus.questionize.models.questions.QuestionType;
import com.zodus.questionize.models.questions.types.enums.Rating;
import com.zodus.questionize.repositories.AnswerRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
@AllArgsConstructor
public class AnswerService {
  private final AnswerRepository answerRepository;

  public Map<Rating, Long> countAllRatingQuestions(StatisticsFilter filter) {
    AnswerSpecificationDTO specificationDTO = new AnswerSpecificationDTO(QuestionType.RATING, filter.questionaryId(), filter.departmentId());
    long veryDissatisfied = answerRepository.count(createSpecification(specificationDTO, Rating.VERY_DISSATISFIED.toString()));
    long dissatisfied = answerRepository.count(createSpecification(specificationDTO, Rating.DISSATISFIED.toString()));
    long neutral = answerRepository.count(createSpecification(specificationDTO, Rating.NEUTRAL.toString()));
    long satisfactory = answerRepository.count(createSpecification(specificationDTO, Rating.SATISFACTORY.toString()));
    long verySatisfactory = answerRepository.count(createSpecification(specificationDTO, Rating.VERY_SATISFACTORY.toString()));
    Map<Rating, Long> response = new TreeMap<>();
    response.put(Rating.VERY_DISSATISFIED, veryDissatisfied);
    response.put(Rating.DISSATISFIED, dissatisfied);
    response.put(Rating.NEUTRAL, neutral);
    response.put(Rating.SATISFACTORY, satisfactory);
    response.put(Rating.VERY_SATISFACTORY, verySatisfactory);
    return response;
  }

  private Specification<Answer> createSpecification(AnswerSpecificationDTO specification, String answer) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();

      if (specification.departmentId() != null) {
        predicates.add(cb.equal(root.get("question").get("questionary").get("department").get("id"), specification.departmentId()));
      }
      if (specification.questionaryId() != null) {
        predicates.add(cb.equal(root.get("question").get("questionary").get("id"), specification.questionaryId()));
      }
      if (answer != null) {
        predicates.add(cb.equal(root.get("answer"), answer));
      }
      if (specification.type() != null) {
        predicates.add(cb.equal(root.get("question").get("type"), specification.type()));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }
}
