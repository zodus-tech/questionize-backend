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
@Table(name = "answer_occurrence")
public class AnswerOccurrence {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String value;

  @Column(nullable = false)
  private Integer numberOfOccurrences;

  @OneToMany(mappedBy = "answerOccurrence", cascade = CascadeType.ALL)
  private Set<Answer> relatedAnswers;

  @ManyToOne
  @JoinColumn(name = "statisticsId", nullable = false)
  private Statistics statistics;
}
