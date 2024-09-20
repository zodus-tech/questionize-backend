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
  private String value;

  @Column(nullable = false)
  private LocalDateTime answeredAt;

  @Column(nullable = false)
  private String issuedBy;

  @ManyToOne
  @JoinColumn(name = "questionId", nullable = false)
  private Question question;

  @ManyToOne
  @JoinColumn(name = "answerOccurrenceId", nullable = false)
  private AnswerOccurrence answerOccurrence;
}
