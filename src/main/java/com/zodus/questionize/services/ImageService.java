package com.zodus.questionize.services;

import com.zodus.questionize.dto.requests.saveImage.SaveImageRequest;
import com.zodus.questionize.models.Image;
import com.zodus.questionize.models.Member;
import com.zodus.questionize.models.Questionary;
import com.zodus.questionize.repositories.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ImageService {
  private final static Base64.Encoder ENCODER = Base64.getEncoder();
  private final static Base64.Decoder DECODER = Base64.getDecoder();
  private final ImageRepository imageRepository;
  private final QuestionaryService questionaryService;
  private final MemberService memberService;

  public Image saveImage(SaveImageRequest request, MultipartFile multipartFile) throws IOException {
    Member member = null;
    Questionary questionary = null;

    if (request.memberId().isPresent()) {
      member = memberService.getMemberById(request.memberId().get());
    }
    if (request.questionaryId().isPresent()) {
      questionary = questionaryService.getQuestionaryById(request.questionaryId().get());
    }

    Image image = Image.builder()
        .name(request.name().orElse(multipartFile.getName()))
        .type(multipartFile.getContentType())
        .imageData(ENCODER.encodeToString(multipartFile.getBytes()))
        .member(member)
        .questionary(questionary)
        .build();

    return imageRepository.save(image);
  }

  public byte[] getImageBytes(UUID id) throws ResponseStatusException {
    Image image = imageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    return DECODER.decode(image.getImageData());
  }

  public byte[] getImageBytes(Image image) {
    return getImageBytes(image.getId());
  }

  public Image getImage(UUID id) throws  ResponseStatusException {
    return imageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
}