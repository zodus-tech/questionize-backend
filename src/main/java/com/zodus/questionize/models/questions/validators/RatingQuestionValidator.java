package com.zodus.questionize.models.questions.validators;

import com.zodus.questionize.models.Answer;
import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.questions.types.RatingQuestion;

public class RatingQuestionValidator implements QuestionValidator {
  @Override
  public boolean validate(Question question, Answer answer) {
    RatingQuestion multipleChoiceQuestion = (RatingQuestion) question;

    return multipleChoiceQuestion.getRatings().contains(answer.getAnswer());
  }
}
