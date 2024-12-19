package com.zodus.questionize.models.questions.validators;

import com.zodus.questionize.models.Answer;
import com.zodus.questionize.models.questions.Question;

public interface QuestionValidator {
  boolean validate(Question question, Answer answer);
}
