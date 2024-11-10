package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.AnswerDTO;
import com.zodus.questionize.models.Answer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnswerDTOFactory {
  public AnswerDTO create(Answer answer) {
    return new AnswerDTO(
        answer.getId(),
        answer.getQuestion().getText(),
        answer.getAnswer()
    );
  }

  public List<AnswerDTO> create(List<Answer> answers) {
    return answers.stream().map(this::create).toList();
  }
}
