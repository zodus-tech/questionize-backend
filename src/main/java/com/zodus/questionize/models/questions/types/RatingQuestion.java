package com.zodus.questionize.models.questions.types;

import com.zodus.questionize.models.Member;
import com.zodus.questionize.models.questions.Question;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RatingQuestion extends Question {
  private List<String> options;

  @ManyToOne
  @JoinColumn(name = "memberId")
  private Member member;
}
