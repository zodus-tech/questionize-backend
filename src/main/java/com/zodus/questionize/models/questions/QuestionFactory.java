package com.zodus.questionize.models.questions;

import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionRequest;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.questions.factories.DefaultQuestionFactory;
import com.zodus.questionize.models.questions.factories.MultipleChoiceQuestionFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuestionFactory {

  public Question create(CreateQuestionRequest questionDTO, Questionary questionary) {
    return switch (questionDTO.type()) {
      case BOOLEAN, TEXT -> DefaultQuestionFactory.create(questionDTO, questionary);
      case MULTIPLE_CHOICE -> MultipleChoiceQuestionFactory.create(questionDTO, questionary);
    };
  }


}
