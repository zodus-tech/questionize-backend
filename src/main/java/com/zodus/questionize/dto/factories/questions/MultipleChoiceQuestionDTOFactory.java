package com.zodus.questionize.dto.factories.questions;

import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.models.questions.types.MultipleChoiceQuestion;
import org.springframework.stereotype.Component;

@Component
public class MultipleChoiceQuestionDTOFactory {
  public QuestionDTO create(MultipleChoiceQuestion question) {
    return new QuestionDTO(question.getId(),  question.getType(), question.getText(), question.getOptions());
  }
}
