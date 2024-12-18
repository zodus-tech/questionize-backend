package com.zodus.questionize.models;

import com.zodus.questionize.infra.customuuid.CustomUUID;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "answer_occurrence")
@AllArgsConstructor
@NoArgsConstructor
public class AnswerOccurrence {
  @Id
  @CustomUUID
  private UUID id;

  @Column(nullable = false)
  private String value;

  @Column(nullable = false)
  private Integer numberOfOccurrences;

  @OneToMany(mappedBy = "answerOccurrence", cascade = CascadeType.ALL)
  private List<Answer> relatedAnswers;

  @ManyToOne
  @JoinColumn(name = "statisticsId", nullable = false)
  private Statistics statistics;
}
