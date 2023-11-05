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
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    /**
     * socialId를 가진 Member가 없으면 새로운 Member생성, 아니면 기존 Member반환
     *
     * @param socialId
     * @return
     */
    public Member findMemberBySocialId(String socialId) {
        return memberRepository.findFirstBySocialId(socialId).orElseGet(() -> createMember(MemberPostRequestDto.builder()
                .socialId(socialId)
                .build()));
    }

    public Member createMember(MemberPostRequestDto memberPostRequestDto) {
        Member member = Member.builder().socialType(SocialType.KAKAO)
                                        .socialId(memberPostRequestDto.socialId)
                                        .nickname(memberPostRequestDto.nickname)
                                        .profileUrl(memberPostRequestDto.profileUrl).build();
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

    public Member verifiedMember(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
    }

}
