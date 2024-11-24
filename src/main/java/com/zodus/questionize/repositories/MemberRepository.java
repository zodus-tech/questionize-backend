package com.zodus.questionize.repositories;

import com.zodus.questionize.models.Department;
import com.zodus.questionize.models.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {
  long countByDepartment(Department department);
  Page<Member> findAllByDepartment(Pageable pageable, Department department);
}
