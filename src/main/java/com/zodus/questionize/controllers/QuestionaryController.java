package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.QuestionaryDTO;
import com.zodus.questionize.dto.factories.QuestionaryDTOFactory;
import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionaryRequest;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.services.QuestionaryService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/questionary")
@AllArgsConstructor
public class QuestionaryController {
  private final QuestionaryService questionaryService;

  @PostMapping("/create")
  public ResponseEntity<QuestionaryDTO> createQuestionary(@RequestBody CreateQuestionaryRequest request) {
    Questionary questionary = questionaryService.createQuestionary(request);

    QuestionaryDTO response = QuestionaryDTOFactory.create(questionary);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<QuestionaryDTO> getQuestionaryById(@PathVariable UUID id) {
    Questionary questionary = questionaryService.getQuestionaryById(id);

    QuestionaryDTO response = QuestionaryDTOFactory.create(questionary);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<PagedModel<QuestionaryDTO>> getQuestionaries(Pageable pageable) {
    Page<Questionary> questionaryPage = questionaryService.getAllQuestionaries(pageable);
    List<QuestionaryDTO> questionaryDTOS = questionaryPage.getContent().stream().map(
        QuestionaryDTOFactory::create
    ).toList();
    Page<QuestionaryDTO> questionaryDTOPage = new PageImpl<>(questionaryDTOS, pageable, questionaryPage.getTotalElements());

    PagedModel<QuestionaryDTO> response = new PagedModel<>(questionaryDTOPage);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<Boolean> deleteQuestionary(@PathVariable UUID id) {
    boolean successfullyDeleted = questionaryService.deleteQuestionaryById(id);

    return new ResponseEntity<>(successfullyDeleted, HttpStatus.OK);
  }
}
