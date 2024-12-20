package com.zodus.questionize.services;

import com.zodus.questionize.dto.requests.questionary.submission.SubmitRequest;
import com.zodus.questionize.models.Answer;
import com.zodus.questionize.models.Member;
import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.Submission;
import com.zodus.questionize.repositories.SubmissionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SubmissionService {
  private final SubmissionRepository submissionRepository;
  private final QuestionaryService questionaryService;
  private final QuestionService questionService;
  private final MemberService memberService;
  private final SubmissionTokenService submissionTokenService;

  public Submission submit(SubmitRequest request, UUID questionaryId, UUID submissionToken) throws ResponseStatusException {
    LocalDateTime submittedAt = LocalDateTime.now();
    Questionary questionary = questionaryService.getQuestionaryById(questionaryId);
    Member member = memberService.getMemberById(request.memberId());

    Submission submission = Submission.builder()
        .submittedAt(submittedAt)
        .questionary(questionary)
        .member(member)
        .build();

    List<Answer> answers = request.answers().stream().map(
        submitAnswerRequest -> {
          Question question = questionService.findById(submitAnswerRequest.questionId());
          Answer answer = Answer.builder()
              .answer(submitAnswerRequest.answer())
              .question(question)
              .submission(submission)
              .build();

          boolean valid = question.getValidator().validate(question, answer);
          if (!valid) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

          return answer;
        }
    ).toList();

    submission.setAnswers(answers);

    submissionTokenService.findAndDelete(submissionToken);
    return submissionRepository.save(submission);
  }

  public Submission getSubmission(UUID questionaryId, UUID id) throws ResponseStatusException {
    return submissionRepository.findByIdAndQuestionaryId(id, questionaryId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Page<Submission> getSubmissions(UUID questionaryId, Pageable pageable) {
    Page<Submission> submissionPage = submissionRepository.findAllByQuestionaryId(pageable, questionaryId);
    long size = submissionRepository.findAllByQuestionaryId(null, questionaryId).getTotalElements();

    return new PageImpl<>(submissionPage.getContent(), pageable, size);
  }

  public long countAllSubmittedBetween(LocalDateTime from, LocalDateTime to) {
    return submissionRepository.countBySubmittedAtBetween(from, to);
  }
}
