package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.dto.QuestionaryDTO;
import com.zodus.questionize.dto.QuestionaryOptionsDTO;
import com.zodus.questionize.models.Question;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.QuestionaryOptions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class QuestionaryDTOFactory {

  public static QuestionaryDTO create(Questionary questionary) {
    QuestionaryOptions questionaryOptions = questionary.getQuestionaryOptions();
    Set<Question> questions = questionary.getQuestions();

    QuestionaryOptionsDTO questionaryOptionsDTO = new QuestionaryOptionsDTO(
        questionaryOptions.getId(),
        questionaryOptions.getStartDate(),
        questionaryOptions.getEndDate(),
        questionaryOptions.getAnswersLimit(),
        questionaryOptions.getAnonymous()
    );

    Set<QuestionDTO> questionDTOS = questions.stream().map(
        question -> new QuestionDTO(question.getId(), question.getText(), question.getQuestionType(), null)
    ).collect(Collectors.toSet());

    return new QuestionaryDTO(
        questionary.getId(),
        questionary.getTitle(),
        questionary.getCreatedAt(),
        questionaryOptionsDTO,
        questionDTOS
    );
  }
}
