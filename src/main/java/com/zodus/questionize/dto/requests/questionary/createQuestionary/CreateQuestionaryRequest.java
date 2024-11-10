package com.zodus.questionize.dto.requests.questionary.createQuestionary;

import java.util.Set;

public record CreateQuestionaryRequest(
  String title,
  CreateQuestionaryOptionsRequest options,
  Set<CreateQuestionRequest> questions
) {
}

