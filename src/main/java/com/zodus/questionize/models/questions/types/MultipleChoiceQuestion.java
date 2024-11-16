package com.zodus.questionize.models.questions.types;

import com.zodus.questionize.models.questions.Question;
import jakarta.persistence.Entity;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MultipleChoiceQuestion extends Question {
  private List<String> options;
}
