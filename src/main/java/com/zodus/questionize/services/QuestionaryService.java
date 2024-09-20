package com.zodus.questionize.services;

import com.zodus.questionize.dto.requests.createQuestionary.CreateQuestionaryRequest;
import com.zodus.questionize.models.Question;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.repositories.QuestionaryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class QuestionaryService {
  private final QuestionaryRepository questionaryRepository;
  public Questionary createQuestionary(CreateQuestionaryRequest request) {
    
    Set<Question> questions = request.questions().stream().map(
        questionDTO -> Question.builder()
            .text(questionDTO.text())
            .questionType(questionDTO.type())
            .build()
    ).collect(Collectors.toSet());

    Questionary questionary = Questionary.builder()
        .title(request.title())
        .createdAt(LocalDateTime.now())
        .startDate(request.options().startDate())
        .endDate(request.options().endDate())
        .answersLimit(request.options().answersLimit())
        .anonymous(request.options().anonymous())
        .questions(questions)
        .build();

    return questionaryRepository.save(questionary);
  }
}
