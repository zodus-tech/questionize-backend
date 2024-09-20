package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.QuestionaryDTO;
import com.zodus.questionize.dto.factories.QuestionaryDTOFactory;
import com.zodus.questionize.dto.requests.createQuestionary.CreateQuestionaryRequest;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.services.QuestionaryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/questionary")
@AllArgsConstructor
public class QuestionaryController {
  private final QuestionaryService questionaryService;

  @PostMapping("/create")
  public ResponseEntity<QuestionaryDTO> createQuestionary(@RequestBody CreateQuestionaryRequest request) {
    Questionary questionary = questionaryService.createQuestionary(request);

    QuestionaryDTO questionaryDTO = QuestionaryDTOFactory.create(questionary);

    return new ResponseEntity<>(questionaryDTO, HttpStatus.CREATED);
  }
}
