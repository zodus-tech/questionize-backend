package com.zodus.questionize.repositories;

import com.zodus.questionize.models.Answer;
import com.zodus.questionize.models.questions.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
  Long countAllByQuestionTypeAndAnswer(QuestionType type, String answer);
}
