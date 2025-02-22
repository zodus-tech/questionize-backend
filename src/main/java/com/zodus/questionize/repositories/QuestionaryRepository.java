package com.zodus.questionize.repositories;

import com.zodus.questionize.models.Questionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.UUID;

@Repository
public interface QuestionaryRepository extends JpaRepository<Questionary, UUID>, JpaSpecificationExecutor<Questionary> {

  long countByStartDateBeforeAndEndDateAfter(LocalDateTime startDateAfter, LocalDateTime endDateBefore);
  long countByStartDateBeforeAndEndDateAfterAndDepartmentId(LocalDateTime startDateAfter, LocalDateTime endDateBefore, UUID departmentId);
}