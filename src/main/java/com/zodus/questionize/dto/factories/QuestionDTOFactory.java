package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.dto.factories.questions.DefaultQuestionDTOFactory;
import com.zodus.questionize.dto.factories.questions.MultipleChoiceQuestionDTOFactory;
import com.zodus.questionize.dto.factories.questions.RatingQuestionDTOFactory;
import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.questions.types.MultipleChoiceQuestion;
import com.zodus.questionize.models.questions.types.RatingQuestion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class QuestionDTOFactory {
  private final DefaultQuestionDTOFactory defaultQuestionDTOFactory;
  private final MultipleChoiceQuestionDTOFactory multipleChoiceQuestionDTOFactory;
  private final RatingQuestionDTOFactory ratingQuestionDTOFactory;

  public QuestionDTO create(Question question) {
    return switch (question.getType()) {
      case BOOLEAN, TEXT -> defaultQuestionDTOFactory.create(question);
      case MULTIPLE_CHOICE -> multipleChoiceQuestionDTOFactory.create((MultipleChoiceQuestion) question);
      case RATING -> ratingQuestionDTOFactory.create((RatingQuestion) question);
    };
  }
}
