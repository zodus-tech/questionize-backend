package com.zodus.questionize.dto.factories;

import com.zodus.questionize.dto.MemberDTO;
import com.zodus.questionize.models.Image;
import com.zodus.questionize.models.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class MemberDTOFactory {
  public MemberDTO create(Member member) {
    Optional<Image> picture = Optional.ofNullable(member.getPicture());
    UUID pictureId = picture.map(Image::getId).orElse(null);

    return new MemberDTO(
        member.getId(),
        member.getName(),
        member.getDepartment().getId(),
        pictureId
    );
  }

  public List<MemberDTO> create(List<Member> members) {
    return members.stream().map(this::create).toList();
  }

  public PagedModel<MemberDTO> create(Page<Member> memberPage, Pageable pageable) {
    List<Member> members = memberPage.getContent();
    List<MemberDTO> memberDTOS = create(members);
    Page<MemberDTO> memberDTOPage = new PageImpl<>(memberDTOS, pageable, memberPage.getTotalElements());
    return new PagedModel<>(memberDTOPage);
  }
}
