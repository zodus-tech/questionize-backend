package com.zodus.questionize.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "answer_occurrence")
public class AnswerOccurrence {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String value;
  private Integer numberOfOccurrences;

  @OneToMany(mappedBy = "answerOccurrence")
  private Set<Answer> relatedAnswers;

  @ManyToOne
  @JoinColumn(name = "statisticsId")
  private Statistics statistics;
}
