package com.zodus.questionize.models.questions.factories;

import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionRequest;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.questions.Question;
import org.springframework.stereotype.Component;

@Component
public class DefaultQuestionFactory implements QuestionFactory {

  @Override
  public Question create(CreateQuestionRequest request, Questionary questionary) {
    Question question = new Question();
    question.setText(request.text());
    question.setQuestionary(questionary);
    question.setType(request.type());

    return question;
  }
}
