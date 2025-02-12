package com.zodus.questionize.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.annotation.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record QuestionaryDTO(
    UUID id,
    String title,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createdAt,
    QuestionaryOptionsDTO options,
    List<QuestionDTO> questions,
    @Nullable
    UUID imageId,
    @Nullable
    UUID submissionToken,
    @Nullable
    UUID departmentId
) {
}
