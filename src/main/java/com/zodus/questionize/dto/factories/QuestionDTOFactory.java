package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.dto.factories.questions.DefaultQuestionDTOFactory;
import com.zodus.questionize.dto.factories.questions.MultipleChoiceQuestionDTOFactory;
import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.questions.types.MultipleChoiceQuestion;

public class QuestionDTOFactory {
  public static QuestionDTO create(Question question) {
    return switch (question.getType()) {
      case BOOLEAN, TEXT -> DefaultQuestionDTOFactory.create(question);
      case MULTIPLE_CHOICE -> MultipleChoiceQuestionDTOFactory.create((MultipleChoiceQuestion) question);
    };
  }
}
