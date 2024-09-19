package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "answer")
public class Answer {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String value;
  private LocalDateTime answeredAt;
  private String issuedBy;

  @ManyToOne
  @JoinColumn(name = "questionId")
  private Question question;

  @ManyToOne
  @JoinColumn(name = "answerOccurrenceId")
  private AnswerOccurrence answerOccurrence;
}
