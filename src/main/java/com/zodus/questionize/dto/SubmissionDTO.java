package com.zodus.questionize.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record SubmissionDTO(
    UUID id,
    String title,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime submittedAt,
    List<AnswerDTO> answers
) {
}
