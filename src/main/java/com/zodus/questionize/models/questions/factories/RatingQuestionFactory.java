package com.zodus.questionize.models.questions.factories;

import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionRequest;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.questions.QuestionType;
import com.zodus.questionize.models.questions.types.RatingQuestion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RatingQuestionFactory {

  public RatingQuestion create(CreateQuestionRequest request, Questionary questionary) {
    RatingQuestion ratingQuestion = new RatingQuestion();
    ratingQuestion.setText(request.text());
    ratingQuestion.setType(QuestionType.RATING);
    ratingQuestion.setQuestionary(questionary);

    return ratingQuestion;
  }
}
