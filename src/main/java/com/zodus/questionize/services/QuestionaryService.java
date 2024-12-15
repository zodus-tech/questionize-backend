package com.zodus.questionize.services;

import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionaryRequest;
import com.zodus.questionize.models.Member;
import com.zodus.questionize.models.questions.QuestionFactory;
import com.zodus.questionize.models.questions.QuestionType;
import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.questions.types.MultipleChoiceQuestion;
import com.zodus.questionize.repositories.QuestionaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionaryService {
  private final QuestionaryRepository questionaryRepository;
  private final QuestionFactory questionFactory;
  private final MemberService memberService;

  public Questionary createQuestionary(CreateQuestionaryRequest request) {
    List<Member> members = request.options().membersIds().stream().map(memberService::getMemberById).toList();

    Questionary questionary = Questionary.builder()
        .title(request.title())
        .createdAt(LocalDateTime.now())
        .startDate(request.options().startDate())
        .endDate(request.options().endDate())
        .answersLimit(request.options().answersLimit())
        .members(members)
        .build();

    List<Question> questions = request.questions().stream().map(
        questionDTO -> questionFactory.create(questionDTO, questionary)
    ).toList();

    questionary.setQuestions(questions);

    return questionaryRepository.save(questionary);
  }

  public Questionary getQuestionaryById(UUID id) throws ResponseStatusException {
    return questionaryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Page<Questionary> getAllQuestionaries(Pageable pageable) {
    return questionaryRepository.findAllByOrderByCreatedAtAsc(pageable);
  }

  public void deleteQuestionaryById(UUID id) throws ResponseStatusException {
    questionaryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    questionaryRepository.deleteById(id);
  }
}
