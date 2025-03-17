package com.zodus.questionize.services;

import com.zodus.questionize.dto.filters.QuestionnairesFilter;
import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionaryRequest;
import com.zodus.questionize.models.Department;
import com.zodus.questionize.models.Member;
import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.questions.types.MultipleChoiceQuestion;
import com.zodus.questionize.models.questions.types.RatingQuestion;
import com.zodus.questionize.repositories.QuestionaryRepository;
import jakarta.persistence.criteria.Predicate;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionaryService {
  private final QuestionaryRepository questionaryRepository;
  private final MemberService memberService;
  private final DepartmentService departmentService;

  public Questionary createQuestionary(CreateQuestionaryRequest request) {
    List<Member> members = request.options().membersIds().stream().map(memberService::getMemberById).toList();

    Department department = departmentService.findById(request.departmentId());

    Questionary questionary = Questionary.builder()
        .title(request.title())
        .createdAt(LocalDateTime.now())
        .startDate(request.options().startDate())
        .endDate(request.options().endDate())
        .answersLimit(request.options().answersLimit())
        .members(members)
        .department(department)
        .build();

    List<Question> questions = request.questions().stream().map(
        questionDTO -> {
          Question question = new Question();
          switch (questionDTO.type()) {
            case RATING -> question = new RatingQuestion();
            case MULTIPLE_CHOICE -> question = new MultipleChoiceQuestion();
          }
          return question.getFactory().create(questionDTO, questionary);
        }
    ).toList();

    questionary.setQuestions(questions);

    return questionaryRepository.save(questionary);
  }

  public Questionary getQuestionaryById(UUID id) throws ResponseStatusException {
    return questionaryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Page<Questionary> getAllQuestionnaires(Pageable pageable, QuestionnairesFilter filter) {
    return questionaryRepository.findAll(createSpecification(filter), pageable);
  }

  public void deleteQuestionaryById(UUID id) throws ResponseStatusException {
    questionaryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    questionaryRepository.deleteById(id);
  }

  public long countAllBetween(LocalDateTime from, LocalDateTime to, UUID departmentId) {
    return departmentId == null ?
        questionaryRepository.countByStartDateBeforeAndEndDateAfter(from, to) :
        questionaryRepository.countByStartDateBeforeAndEndDateAfterAndDepartmentId(from, to, departmentId);
  }

  public void saveQuestionary(Questionary questionary) {
    questionaryRepository.save(questionary);
  }

  private Specification<Questionary> createSpecification(QuestionnairesFilter filter) {
    return (root, query, cb) -> {
      List<Predicate> predicates = new ArrayList<>();
      if (filter.title() != null) {
        predicates.add(cb.equal(cb.lower(root.get("title")), filter.title().toLowerCase()));
      }
      if (filter.departmentId() != null) {
        predicates.add(cb.equal(root.get("department").get("id"), filter.departmentId()));
      }
      if (filter.active() != null) {
        predicates.add(cb.lessThanOrEqualTo(root.get("startDate"), cb.currentTimestamp()));
        predicates.add(cb.greaterThanOrEqualTo(root.get("endDate"), cb.currentTimestamp()));
      }

      return cb.and(predicates.toArray(new Predicate[0]));
    };
  }

  public Questionary renameQuestionary(UUID id, String name) throws ResponseStatusException {
    Questionary questionary = questionaryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    questionary.setTitle(name);
    return questionaryRepository.save(questionary);
  }
}
