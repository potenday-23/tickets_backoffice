package project.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.member.dto.MemberPatchRequestDto;
import project.backend.domain.member.dto.MemberPostRequestDto;
import project.backend.domain.member.entity.SocialType;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.mapper.MemberMapper;
import project.backend.domain.member.repository.MemberRepository;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MembrService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public Member createMember(MemberPostRequestDto memberPostRequestDto){
        Member member = Member.builder().socialType(SocialType.KAKAO).socialId(memberPostRequestDto.socialId).build();
        memberRepository.save(member);
        return member;
    }

    public Member getMember(Long id) {
        return verifiedMember(id);
    }

    public List<Member> getMemberList() {
        return memberRepository.findAll();
    }

    public Member patchMember(Long id, MemberPatchRequestDto memberPatchRequestDto) {
        Member member = verifiedMember(id).patchMember(memberPatchRequestDto);
        memberRepository.save(member);
        return member;
    }

    public void deleteMember(Long id) {
        memberRepository.delete(verifiedMember(id));
    }

    private Member verifiedMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

}
