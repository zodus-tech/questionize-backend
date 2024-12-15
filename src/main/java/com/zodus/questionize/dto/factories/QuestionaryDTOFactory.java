package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.MemberDTO;
import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.dto.QuestionaryDTO;
import com.zodus.questionize.dto.QuestionaryOptionsDTO;
import com.zodus.questionize.models.Image;
import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.Questionary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class QuestionaryDTOFactory {
  private final QuestionDTOFactory questionDTOFactory;
  private final MemberDTOFactory memberDTOFactory;

  public QuestionaryDTO create(Questionary questionary) {
    List<Question> questions = questionary.getQuestions();
    List<MemberDTO> memberDTOS = questionary.getMembers().stream().map(memberDTOFactory::create).toList();

    QuestionaryOptionsDTO questionaryOptionsDTO = new QuestionaryOptionsDTO(
        questionary.getStartDate(),
        questionary.getEndDate(),
        questionary.getAnswersLimit(),
        memberDTOS
    );

    List<QuestionDTO> questionDTOS = questions.stream().map(questionDTOFactory::create).toList();

    Optional<Image> banner = Optional.ofNullable(questionary.getBanner());
    UUID bannerId = banner.map(Image::getId).orElse(null);

    return new QuestionaryDTO(
        questionary.getId(),
        questionary.getTitle(),
        questionary.getCreatedAt(),
        questionaryOptionsDTO,
        questionDTOS,
        bannerId
    );
  }
}
