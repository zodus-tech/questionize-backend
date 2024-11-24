package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.QuestionDTO;
import com.zodus.questionize.dto.QuestionaryDTO;
import com.zodus.questionize.dto.QuestionaryOptionsDTO;
import com.zodus.questionize.models.Image;
import com.zodus.questionize.models.questions.Question;
import com.zodus.questionize.models.Questionary;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class QuestionaryDTOFactory {

  public static QuestionaryDTO create(Questionary questionary) {
    Set<Question> questions = questionary.getQuestions();

    QuestionaryOptionsDTO questionaryOptionsDTO = new QuestionaryOptionsDTO(
        questionary.getStartDate(),
        questionary.getEndDate(),
        questionary.getAnswersLimit()
    );

    Set<QuestionDTO> questionDTOS = questions.stream().map(QuestionDTOFactory::create).collect(Collectors.toSet());

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
