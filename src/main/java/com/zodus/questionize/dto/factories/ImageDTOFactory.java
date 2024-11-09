package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.ImageDTO;
import com.zodus.questionize.models.Image;
import com.zodus.questionize.models.Member;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@AllArgsConstructor
public class ImageDTOFactory {
  private final ImageService imageService;

  public ImageDTO create(Image image) {
    Optional<Member> imageMemberOptional = Optional.ofNullable(image.getMember());
    Optional<Questionary> imageQuestionaryOptional = Optional.ofNullable(image.getQuestionary());

    UUID memberId = imageMemberOptional.map(Member::getId).orElse(null);
    UUID questionaryId = imageQuestionaryOptional.map(Questionary::getId).orElse(null);

    return new ImageDTO(
        image.getId(),
        image.getName(),
        imageService.getImageBytes(image),
        memberId,
        questionaryId
    );
  }
}
