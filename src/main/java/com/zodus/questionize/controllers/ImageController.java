package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.ImageDTO;
import com.zodus.questionize.dto.factories.ImageDTOFactory;
import com.zodus.questionize.dto.requests.saveImage.SaveImageRequest;
import com.zodus.questionize.models.Image;
import com.zodus.questionize.services.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/images")
@AllArgsConstructor
public class ImageController {
  private final ImageService imageService;
  private final ImageDTOFactory imageDTOFactory;

  @GetMapping("/{id}")
  private ResponseEntity<ImageDTO> getImageById(@PathVariable UUID id) {
    Image image = imageService.getImage(id);
    ImageDTO response = imageDTOFactory.create(image);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/save")
  private ResponseEntity<ImageDTO> saveImage(@RequestPart("request") SaveImageRequest request, @RequestPart("imageFile") MultipartFile multipartFile) throws IOException {
    Image image = imageService.saveImage(request, multipartFile);
    ImageDTO response = imageDTOFactory.create(image);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
