package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.MemberDTO;
import com.zodus.questionize.dto.factories.MemberDTOFactory;
import com.zodus.questionize.dto.requests.createmember.MemberRequest;
import com.zodus.questionize.models.Department;
import com.zodus.questionize.models.Member;
import com.zodus.questionize.services.DepartmentService;
import com.zodus.questionize.services.MemberService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {
  private final MemberService memberService;
  private final MemberDTOFactory memberDTOFactory;
  private final DepartmentService departmentService;

  @PostMapping("/create")
  public ResponseEntity<MemberDTO> createMember(@RequestBody MemberRequest request) {
    Member member = memberService.createMember(request);
    MemberDTO response = memberDTOFactory.create(member);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<MemberDTO> findMemberById(@PathVariable UUID id) {
    Member member = memberService.getMemberById(id);
    MemberDTO response = memberDTOFactory.create(member);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<PagedModel<MemberDTO>> findAllMembers(Pageable pageable) {
    Page<Member> member = memberService.getAllMembers(pageable);
    PagedModel<MemberDTO> response = memberDTOFactory.create(member, pageable);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/department/{id}/all")
  public ResponseEntity<PagedModel<MemberDTO>> findAllMembersByDepartment(Pageable pageable, @PathVariable UUID id) {
    Department department = departmentService.findById(id);
    Page<Member> member = memberService.getAllMembersByDepartment(pageable, department);
    PagedModel<MemberDTO> response = memberDTOFactory.create(member, pageable);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PatchMapping("/update/{id}")
  public ResponseEntity<MemberDTO> updateMember(@RequestBody MemberRequest request, @PathVariable UUID id) {
    Member member = memberService.updateMember(request, id);
    MemberDTO response = memberDTOFactory.create(member);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteMember(@PathVariable UUID id) {
    memberService.deleteMember(id);

    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }
}
