package com.zodus.questionize.dto.requests.questionary.submission;

import java.util.List;

public record SubmitRequest(
    List<SubmitAnswerRequest> answers
) {
}
