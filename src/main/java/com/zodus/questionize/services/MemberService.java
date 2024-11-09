package com.zodus.questionize.services;

import com.zodus.questionize.models.Member;
import com.zodus.questionize.repositories.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@AllArgsConstructor
public class MemberService {
  private final MemberRepository memberRepository;

  public Member getMemberById(UUID id) throws ResponseStatusException {
    return memberRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }
}
