package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "statistics")
public class Statistics {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private Integer totalAnswers;

  @OneToOne
  @JoinColumn(name = "questionId", nullable = false)
  private Question question;

  @OneToMany(mappedBy = "statistics", cascade = CascadeType.ALL)
  private Set<AnswerOccurrence> answerOccurrences;
}
