package com.zodus.questionize.models.questions.factories;

import com.zodus.questionize.dto.requests.questionary.createQuestionary.CreateQuestionRequest;
import com.zodus.questionize.models.Member;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.models.questions.QuestionType;
import com.zodus.questionize.models.questions.types.RatingQuestion;
import com.zodus.questionize.repositories.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RatingQuestionFactory {
  private final MemberRepository memberRepository;

  public RatingQuestion create(CreateQuestionRequest request, Questionary questionary) {
    RatingQuestion ratingQuestion = new RatingQuestion();
    ratingQuestion.setText(request.text());
    ratingQuestion.setType(QuestionType.RATING);
    ratingQuestion.setQuestionary(questionary);
    ratingQuestion.setOptions(request.options());

    assert request.memberId() != null;
    Member member = memberRepository.findById(request.memberId()).orElseThrow();
    ratingQuestion.setMember(member);

    return ratingQuestion;
  }
}
