package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.SubmissionDTO;
import com.zodus.questionize.dto.factories.SubmissionDTOFactory;
import com.zodus.questionize.dto.requests.questionary.submission.SubmitRequest;
import com.zodus.questionize.models.Submission;
import com.zodus.questionize.services.SubmissionsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/questionary/{questionaryId}")
@AllArgsConstructor
public class SubmissionsController {
  private final SubmissionsService submissionsService;
  private final SubmissionDTOFactory submissionDTOFactory;

  @PostMapping("/submit")
  public ResponseEntity<SubmissionDTO> submit(@RequestBody SubmitRequest request, @PathVariable UUID questionaryId) {
    Submission submission = submissionsService.submit(request, questionaryId);
    SubmissionDTO submissionDTO = submissionDTOFactory.create(submission);

    return new ResponseEntity<>(submissionDTO, HttpStatus.OK);
  }
}
