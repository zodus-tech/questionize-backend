package com.zodus.questionize.models;

import com.zodus.questionize.models.questions.Question;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "statistics")
@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private Integer totalAnswers;

  @OneToOne
  @JoinColumn(name = "questionId", nullable = false)
  private Question question;

  @OneToMany(mappedBy = "statistics", cascade = CascadeType.ALL)
  private List<AnswerOccurrence> answerOccurrences;
}
