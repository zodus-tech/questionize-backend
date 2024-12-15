package com.zodus.questionize.repositories;

import com.zodus.questionize.models.SubmissionToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SubmissionTokenRepository extends JpaRepository<SubmissionToken, UUID> {
}
