package com.zodus.questionize.models.questions.factories;

import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionRequest;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.questions.QuestionType;
import com.zodus.questionize.models.questions.types.MultipleChoiceQuestion;
import org.springframework.stereotype.Component;

@Component
public class MultipleChoiceQuestionFactory {
  public MultipleChoiceQuestion create(CreateQuestionRequest request, Questionary questionary) {
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion();
    multipleChoiceQuestion.setText(request.text());
    multipleChoiceQuestion.setType(QuestionType.MULTIPLE_CHOICE);
    multipleChoiceQuestion.setQuestionary(questionary);
    multipleChoiceQuestion.setOptions(request.options());

    return multipleChoiceQuestion;
  }
}
