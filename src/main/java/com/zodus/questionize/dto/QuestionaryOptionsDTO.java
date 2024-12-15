package com.zodus.questionize.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record QuestionaryOptionsDTO(
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime startDate,
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime endDate,
    Integer answersLimit,
    List<MemberDTO> members
) {
}
