package com.zodus.questionize.repositories;

import com.zodus.questionize.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, UUID>, JpaSpecificationExecutor<Submission> {
  Optional<Submission> findByIdAndQuestionaryId(UUID id, UUID questionaryId);
}
