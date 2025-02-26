package com.zodus.questionize.services;

import com.zodus.questionize.dto.requests.createmember.MemberRequest;
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

  public Member createMember(MemberRequest memberRequest) {
    Department department = departmentService.findById(memberRequest.departmentId());
    Member member = Member.builder()
        .name(memberRequest.name())
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

  public void deleteMember(UUID id) throws ResponseStatusException {
    Member member = memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    memberRepository.delete(member);
  }

  public Member updateMember(MemberRequest request, UUID id) throws ResponseStatusException {
    Member member = memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    if (request.name() != null) member.setName(request.name());
    if (request.departmentId() != null) {
      Department department = departmentService.findById(request.departmentId());
      member.setDepartment(department);
    }

    return memberRepository.save(member);
  }

  public Page<Member> getAllMembersByDepartment(Pageable pageable, Department department) {
    Page<Member> memberPage = memberRepository.findAllByDepartment(pageable, department);
    long size = memberRepository.countByDepartment(department);

    return new PageImpl<>(memberPage.getContent(), pageable, size);
  }

  public void saveMember(Member member) {
    memberRepository.save(member);
  }
}
