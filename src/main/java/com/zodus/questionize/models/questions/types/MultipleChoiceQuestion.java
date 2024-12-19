package com.zodus.questionize.models.questions.types;

import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.questions.factories.MultipleChoiceQuestionFactory;
import com.zodus.questionize.models.questions.validators.MultipleChoicheQuestionValidator;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class MultipleChoiceQuestion extends Question {
  private List<String> options;

  public MultipleChoiceQuestion() {
    super(new MultipleChoicheQuestionValidator(), new MultipleChoiceQuestionFactory());
  }
}
