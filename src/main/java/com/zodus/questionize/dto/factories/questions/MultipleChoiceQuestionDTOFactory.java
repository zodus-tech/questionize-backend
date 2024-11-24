package com.zodus.questionize.dto.factories.questions;

import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.models.questions.types.MultipleChoiceQuestion;

public class MultipleChoiceQuestionDTOFactory {
  public static QuestionDTO create(MultipleChoiceQuestion question) {
    return new QuestionDTO(question.getId(),  question.getType(), question.getText(), question.getOptions());
  }
}
