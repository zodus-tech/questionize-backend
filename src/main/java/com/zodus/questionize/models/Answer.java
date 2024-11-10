package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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
  @GeneratedValue(strategy = GenerationType.UUID)
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
