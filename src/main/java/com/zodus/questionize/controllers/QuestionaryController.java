package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.QuestionaryDTO;
import com.zodus.questionize.dto.factories.QuestionaryDTOFactory;
import com.zodus.questionize.dto.requests.createQuestionary.CreateQuestionaryRequest;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.services.QuestionaryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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

  @GetMapping("/{id}")
  public ResponseEntity<QuestionaryDTO> getQuestionaryById(@PathVariable UUID id) {
    Questionary questionary = questionaryService.getQuestionaryById(id);
    QuestionaryDTO questionaryDTO = QuestionaryDTOFactory.create(questionary);

    return new ResponseEntity<>(questionaryDTO, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<Page<QuestionaryDTO>> getQuestionaries(Pageable pageable) {
    return null;
  }
}
