package com.zodus.questionize.services;

import com.zodus.questionize.dto.requests.createQuestionary.CreateQuestionaryRequest;
import com.zodus.questionize.enums.QuestionType;
import com.zodus.questionize.models.Question;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.repositories.QuestionaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionaryService {
  private final QuestionaryRepository questionaryRepository;
  public Questionary createQuestionary(CreateQuestionaryRequest request) {
    Questionary questionary = Questionary.builder()
        .title(request.title())
        .createdAt(LocalDateTime.now())
        .startDate(request.options().startDate())
        .endDate(request.options().endDate())
        .answersLimit(request.options().answersLimit())
        .build();

    Set<Question> questions = request.questions().stream().map(
        questionDTO -> {
          if (questionDTO.options() != null && !questionDTO.type().equals(QuestionType.MULTIPLE_CHOICE)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
          if (questionDTO.options() == null && questionDTO.type().equals(QuestionType.MULTIPLE_CHOICE)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

          return Question.builder()
              .text(questionDTO.text())
              .questionType(questionDTO.type())
              .questionary(questionary)
              .options(questionDTO.options())
              .build();
        }
    ).collect(Collectors.toSet());

    questionary.setQuestions(questions);

    return questionaryRepository.save(questionary);
  }

  public Questionary getQuestionaryById(UUID id) throws ResponseStatusException {
    return questionaryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Page<Questionary> getAllQuestionaries(Pageable pageable) {
    return questionaryRepository.findAll(pageable);
  }

  public boolean deleteQuestionaryById(UUID id) throws ResponseStatusException {
    questionaryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    questionaryRepository.deleteById(id);
    return questionaryRepository.findById(id).isEmpty();
  }
}
