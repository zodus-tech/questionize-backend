package com.zodus.questionize.models.questions.factories;

import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionRequest;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.questions.Question;

public interface QuestionFactory {
  Question create(CreateQuestionRequest request, Questionary questionary);
}
