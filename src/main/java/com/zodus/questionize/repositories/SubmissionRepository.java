package com.zodus.questionize.repositories;

import com.zodus.questionize.models.Submission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, UUID> {
  Optional<Submission> findByIdAndQuestionaryId(UUID id, UUID questionaryId);
  Page<Submission> findAllByQuestionaryId(Pageable pageable, UUID questionaryId);
}
