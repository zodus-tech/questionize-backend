package com.zodus.questionize.repositories;

import com.zodus.questionize.models.Questionary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionaryRepository extends JpaRepository<Questionary, UUID> {
}
