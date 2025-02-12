package com.zodus.questionize.dto.requests.questionary.createQuestionary;

import java.util.List;
import java.util.UUID;

public record CreateQuestionaryRequest(
  String title,
  CreateQuestionaryOptionsRequest options,
  List<CreateQuestionRequest> questions,
  UUID departmentId
) {
}

