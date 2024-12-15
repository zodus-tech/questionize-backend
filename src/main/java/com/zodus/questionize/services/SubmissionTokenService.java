package com.zodus.questionize.services;

import com.zodus.questionize.models.SubmissionToken;
import com.zodus.questionize.repositories.SubmissionTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SubmissionTokenService {
  private final SubmissionTokenRepository submissionTokenRepository;

  public SubmissionToken generate() {
    SubmissionToken submissionToken = new SubmissionToken();
    submissionToken.setCreatedAt(LocalDateTime.now());
    return submissionTokenRepository.save(submissionToken);
  }

  public void findAndDelete(UUID id) throws ResponseStatusException {
    submissionTokenRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    submissionTokenRepository.deleteById(id);
  }

  public long countTotal() {
    return submissionTokenRepository.count();
  }
}
