package com.zodus.questionize.models.questions.validators;

import com.zodus.questionize.models.Answer;
import com.zodus.questionize.models.questions.Question;

public class DefaultQuestionValidator implements QuestionValidator {
  @Override
  public boolean validate(Question question, Answer answer) {
    return true;
  }
}
