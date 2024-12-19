package com.zodus.questionize.models.questions.validators;

import com.zodus.questionize.models.Answer;
import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.questions.types.MultipleChoiceQuestion;

public class MultipleChoicheQuestionValidator implements QuestionValidator {
  @Override
  public boolean validate(Question question, Answer answer) {
    MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) question;

    return multipleChoiceQuestion.getOptions().contains(answer.getAnswer());
  }
}
