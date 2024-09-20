package com.zodus.questionize.repositories;

import com.zodus.questionize.models.AnswerOccurrence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AnswerOccurrenceRepository extends JpaRepository<AnswerOccurrence, UUID> {
}
