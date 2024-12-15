package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.SubmissionDTO;
import com.zodus.questionize.dto.factories.SubmissionDTOFactory;
import com.zodus.questionize.dto.requests.questionary.submission.SubmitRequest;
import com.zodus.questionize.models.Submission;
import com.zodus.questionize.services.SubmissionsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
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
  public ResponseEntity<SubmissionDTO> submit(@RequestBody SubmitRequest request, @PathVariable UUID questionaryId, @RequestParam UUID submissionToken) {
    Submission submission = submissionsService.submit(request, questionaryId, submissionToken);
    SubmissionDTO response = submissionDTOFactory.create(submission);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/submissions")
  public ResponseEntity<PagedModel<SubmissionDTO>> getSubmissions(@PathVariable UUID questionaryId, Pageable pageable) {
    Page<Submission> submissionsPage = submissionsService.getSubmissions(questionaryId, pageable);
    PagedModel<SubmissionDTO> response = submissionDTOFactory.create(submissionsPage, pageable);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/submissions/{id}")
  public ResponseEntity<SubmissionDTO> getSubmission(@PathVariable UUID questionaryId, @PathVariable UUID id) {
    Submission submission = submissionsService.getSubmission(questionaryId, id);
    SubmissionDTO response = submissionDTOFactory.create(submission);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
