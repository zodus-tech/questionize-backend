package com.zodus.questionize.dto.factories.questions;

import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.models.questions.types.RatingQuestion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RatingQuestionDTOFactory {

  public QuestionDTO create(RatingQuestion question) {
    return new QuestionDTO(question.getId(),  question.getType(), question.getText(), question.getOptions());
  }
}
