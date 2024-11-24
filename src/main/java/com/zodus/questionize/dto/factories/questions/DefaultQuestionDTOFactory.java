package com.zodus.questionize.dto.factories.questions;

import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.models.questions.Question;

public class DefaultQuestionDTOFactory {
  public static QuestionDTO create(Question question) {
    return new QuestionDTO(question.getId(), question.getType(), question.getText(), null);
  }
}
