package com.zodus.questionize.models.questions.validators;

import com.zodus.questionize.models.Answer;
import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.questions.types.MultipleChoiceQuestion;

public class MultipleChoicheQuestionValidator implements QuestionValidator {
  @Override
  public boolean validate(Question question, Answer answer) {
    MultipleChoiceQuestion multipleChoiceQuestion = (MultipleChoiceQuestion) question;
    String answerToString = answer.getAnswer();
    String[] answersToArray = answerToString.split(", ");

    for (String a : answersToArray) {
      if (!multipleChoiceQuestion.getOptions().contains(a))
        return false;
    }

    return true;
  }
}
