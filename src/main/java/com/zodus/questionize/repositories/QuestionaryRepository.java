package com.zodus.questionize.repositories;

import com.zodus.questionize.models.Questionary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface QuestionaryRepository extends JpaRepository<Questionary, UUID> {
  Page<Questionary> findAllByOrderByCreatedAtAsc(Pageable pageable);

  long countByStartDateBeforeAndEndDateAfter(LocalDateTime startDateAfter, LocalDateTime endDateBefore);
}