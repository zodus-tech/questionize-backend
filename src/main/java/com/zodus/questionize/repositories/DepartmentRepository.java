package com.zodus.questionize.repositories;

import com.zodus.questionize.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
  Optional<Department> findByName(String name);
}
