package com.zodus.questionize.services;

import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.repositories.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionService {
  private final QuestionRepository questionRepository;

  public Question findById(UUID id) throws ResponseStatusException {
    return questionRepository.findById(id).orElseThrow(
        () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
    );
  }
}
