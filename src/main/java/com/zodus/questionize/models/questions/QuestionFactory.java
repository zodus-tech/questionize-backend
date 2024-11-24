package com.zodus.questionize.models.questions;

import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionRequest;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.questions.factories.DefaultQuestionFactory;
import com.zodus.questionize.models.questions.factories.MultipleChoiceQuestionFactory;
import com.zodus.questionize.models.questions.factories.RatingQuestionFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class QuestionFactory {
  private final RatingQuestionFactory ratingQuestionFactory;
  private final MultipleChoiceQuestionFactory multipleChoiceQuestionFactory;
  private final DefaultQuestionFactory defaultQuestionFactory;

  public Question create(CreateQuestionRequest questionDTO, Questionary questionary) {
    return switch (questionDTO.type()) {
      case BOOLEAN, TEXT -> defaultQuestionFactory.create(questionDTO, questionary);
      case MULTIPLE_CHOICE -> multipleChoiceQuestionFactory.create(questionDTO, questionary);
      case RATING -> ratingQuestionFactory.create(questionDTO, questionary);
    };
  }


}
