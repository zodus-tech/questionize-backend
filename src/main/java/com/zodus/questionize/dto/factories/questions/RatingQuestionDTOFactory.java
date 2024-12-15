package com.zodus.questionize.dto.factories.questions;

import com.zodus.questionize.dto.MemberDTO;
import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.dto.factories.MemberDTOFactory;
import com.zodus.questionize.models.questions.types.RatingQuestion;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RatingQuestionDTOFactory {
  private final MemberDTOFactory memberDTOFactory;

  public QuestionDTO create(RatingQuestion question) {
    MemberDTO memberDTO = memberDTOFactory.create(question.getMember());
    return new QuestionDTO(question.getId(),  question.getType(), question.getText(), question.getOptions(), memberDTO);
  }
}
