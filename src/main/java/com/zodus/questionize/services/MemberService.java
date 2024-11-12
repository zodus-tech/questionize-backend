package com.zodus.questionize.services;

import com.zodus.questionize.dto.requests.createMember.CreateMemberRequest;
import com.zodus.questionize.models.Department;
import com.zodus.questionize.models.Member;
import com.zodus.questionize.repositories.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;
  private final DepartmentService departmentService;

  public Member createMember(CreateMemberRequest createMemberRequest) {
    Department department = departmentService.findById(createMemberRequest.departmentId());
    Member member = Member.builder()
        .name(createMemberRequest.name())
        .department(department)
        .build();

    return memberRepository.save(member);
  }

  public Member getMemberById(UUID id) throws ResponseStatusException {
    return memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Page<Member> getAllMembers(Pageable pageable) {
    Page<Member> memberPage = memberRepository.findAll(pageable);
    long size = memberRepository.count();

    return new PageImpl<>(memberPage.getContent(), pageable, size);
  }
}
