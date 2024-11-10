package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.AnswerDTO;
import com.zodus.questionize.dto.SubmissionDTO;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.Submission;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class SubmissionDTOFactory {
  private final AnswerDTOFactory answerDTOFactory;

  public SubmissionDTO create(Submission submission) {
    List<AnswerDTO> answerDTOS = answerDTOFactory.create(submission.getAnswers().stream().toList());
    Questionary questionary = submission.getQuestionary();

    return new SubmissionDTO(
        submission.getId(),
        questionary.getTitle(),
        submission.getSubmittedAt(),
        answerDTOS
    );
  }

  public List<SubmissionDTO> create(List<Submission> submissions) {
    return submissions.stream().map(this::create).toList();
  }
}
