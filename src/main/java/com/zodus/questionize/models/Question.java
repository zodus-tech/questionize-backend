package com.zodus.questionize.models;

import com.zodus.questionize.enums.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@Table(name = "question")
@AllArgsConstructor
@NoArgsConstructor
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String text;

  @Column(nullable = false)
  private QuestionType questionType;

  @ManyToOne
  @JoinColumn(name = "questionaryId")
  private Questionary questionary;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  private Set<Answer> answers;

  @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
  private Statistics statistics;
}
