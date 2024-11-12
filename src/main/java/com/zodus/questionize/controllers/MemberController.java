package com.zodus.questionize.controllers;

import com.zodus.questionize.dto.MemberDTO;
import com.zodus.questionize.dto.factories.MemberDTOFactory;
import com.zodus.questionize.dto.requests.createMember.CreateMemberRequest;
import com.zodus.questionize.models.Member;
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

  @PostMapping("/create")
  public ResponseEntity<MemberDTO> createMember(@RequestBody CreateMemberRequest request) {
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
}
