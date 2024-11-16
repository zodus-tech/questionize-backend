package com.zodus.questionize.models.questions.factories;

import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionRequest;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.questions.Question;
public class DefaultQuestionFactory {
  public static Question create(CreateQuestionRequest request, Questionary questionary) {
    return Question.builder()
        .text(request.text())
        .questionary(questionary)
        .type(request.type())
        .build();
  }
}
