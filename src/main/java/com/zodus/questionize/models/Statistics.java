package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "statistics")
public class Statistics {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private Integer totalAnswers;

  @OneToOne
  @JoinColumn(name = "questionId")
  private Question question;

  @OneToMany(mappedBy = "statistics")
  private Set<AnswerOccurrence> answerOccurrences;
}
