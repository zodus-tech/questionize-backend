package com.zodus.questionize.models.questions;

import com.zodus.questionize.models.Answer;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.Statistics;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Question {
  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  protected UUID id;

  protected String text;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "questionaryId")
  protected Questionary questionary;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  protected List<Answer> answers;

  @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
  protected Statistics statistics;

  @Enumerated(EnumType.STRING)
  protected QuestionType type;
}
