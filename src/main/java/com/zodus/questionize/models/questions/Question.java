package com.zodus.questionize.models.questions;

import com.zodus.questionize.infra.customuuid.CustomUUID;
import com.zodus.questionize.models.Answer;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.Statistics;
import com.zodus.questionize.models.questions.factories.DefaultQuestionFactory;
import com.zodus.questionize.models.questions.factories.QuestionFactory;
import com.zodus.questionize.models.questions.validators.DefaultQuestionValidator;
import com.zodus.questionize.models.questions.validators.QuestionValidator;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {
  @Id
  @CustomUUID
  protected UUID id;

  protected String text;

  @ManyToOne
  @JoinColumn(name = "questionaryId")
  protected Questionary questionary;

  @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
  protected List<Answer> answers;

  @OneToOne(mappedBy = "question", cascade = CascadeType.ALL)
  protected Statistics statistics;

  @Enumerated(EnumType.STRING)
  protected QuestionType type;

  @Transient
  protected QuestionValidator validator = new DefaultQuestionValidator();

  @Transient
  protected QuestionFactory factory = new DefaultQuestionFactory();

  protected Question(QuestionValidator validator, QuestionFactory factory) {
    this.validator = validator;
    this.factory = factory;
  }
}
