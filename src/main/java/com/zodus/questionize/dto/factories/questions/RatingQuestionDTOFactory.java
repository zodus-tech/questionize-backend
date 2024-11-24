package com.zodus.questionize.dto.factories.questions;

import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.models.questions.types.RatingQuestion;

public class RatingQuestionDTOFactory {
  public static QuestionDTO create(RatingQuestion question) {
    return new QuestionDTO(question.getId(),  question.getType(), question.getText(), question.getOptions(), question.getMember().getId());
  }
}
