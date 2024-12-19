package com.zodus.questionize.models.questions.types;

import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.questions.types.enums.Rating;
import com.zodus.questionize.models.questions.validators.RatingQuestionValidator;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class RatingQuestion extends Question {
  private List<String> ratings = List.of(
      Rating.VERY_DISSATISFIED.toString(),
      Rating.DISSATISFIED.toString(),
      Rating.NEUTRAL.toString(),
      Rating.SATISFACTORY.toString(),
      Rating.VERY_SATISFACTORY.toString()
  );

  public RatingQuestion() {
    super(new RatingQuestionValidator());
  }
}
