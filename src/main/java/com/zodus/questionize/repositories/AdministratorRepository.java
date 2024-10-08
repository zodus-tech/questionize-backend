package com.zodus.questionize.repositories;

import com.zodus.questionize.models.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AdministratorRepository extends JpaRepository<Administrator, UUID> {
  Optional<Administrator> findByUsername(String username);
}
