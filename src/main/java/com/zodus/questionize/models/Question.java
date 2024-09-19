package com.zodus.questionize.models;

import com.zodus.questionize.enums.QuestionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "question")
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String text;
  private QuestionType questionType;

  @ManyToOne
  @JoinColumn(name = "questionaryId")
  private Questionary questionary;

  @OneToMany(mappedBy = "question")
  private Set<Answer> answers;

  @OneToOne(mappedBy = "question")
  private Statistics statistics;
}
