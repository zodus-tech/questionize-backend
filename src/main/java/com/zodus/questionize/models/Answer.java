package com.zodus.questionize.models;

import com.zodus.questionize.infra.customuuid.CustomUUID;
import com.zodus.questionize.models.questions.Question;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "answer")
@AllArgsConstructor
@NoArgsConstructor
public class Answer {
  @Id
  @CustomUUID
  private UUID id;

  @Column(nullable = false)
  private String answer;

  @ManyToOne
  @JoinColumn(name = "questionId", nullable = false)
  private Question question;

  @ManyToOne
  @JoinColumn(name = "answerOccurrenceId")
  private AnswerOccurrence answerOccurrence;

  @ManyToOne
  @JoinColumn(name = "submissionId", nullable = false)
  private Submission submission;
}
