package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.AnswerDTO;
import com.zodus.questionize.dto.SubmissionDTO;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.Submission;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
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

  public PagedModel<SubmissionDTO> create(Page<Submission> submissionPage, Pageable pageable) {
    List<Submission> submissions = submissionPage.getContent();
    List<SubmissionDTO> submissionDTOS = create(submissions);
    Page<SubmissionDTO> submissionDTOPage = new PageImpl<>(submissionDTOS, pageable, submissionPage.getTotalElements());
    return new PagedModel<>(submissionDTOPage);
  }
}
