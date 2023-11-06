package project.backend.domain.memberTicketLike.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.service.MemberService;
import project.backend.domain.memberTicketLike.dto.MemberTicketLikePostRequestDto;
import project.backend.domain.memberTicketLike.mapper.MemberTicketLikeMapper;
import project.backend.domain.memberTicketLike.entity.MemberTicketLike;
import project.backend.domain.memberTicketLike.repository.MemberTicketLikeRepository;
import project.backend.domain.ticket.entity.Ticket;
import project.backend.domain.ticket.service.TicketService;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberTicketLikeService {
    private final MemberTicketLikeRepository memberTicketLikeRepository;
    private final MemberTicketLikeMapper memberTicketLikeMapper;
    private final MemberService memberService;
    private final TicketService ticketService;

    public MemberTicketLike createMemberTicketLike(MemberTicketLikePostRequestDto memberTicketLikePostRequestDto){

        // member와 ticket조회
        Member member = memberService.verifiedMember(memberTicketLikePostRequestDto.getMemberId());
        Ticket ticket = ticketService.verifiedTicket(memberTicketLikePostRequestDto.getTicketId());

        // 찜하기 생성
        MemberTicketLike memberTicketLike = MemberTicketLike.builder()
                .member(member)
                .ticket(ticket).build();

        // 연관관계 매핑
        memberTicketLike.setMemberTicket(member, ticket);
        memberTicketLikeRepository.save(memberTicketLike);
        return memberTicketLike;
    }

    public MemberTicketLike getMemberTicketLike(Long id) {
        return verifiedMemberTicketLike(id);
    }

    public List<MemberTicketLike> getMemberTicketLikeList() {
        return memberTicketLikeRepository.findAll();
    }

    public void deleteMemberTicketLike(Long id) {
        memberTicketLikeRepository.delete(verifiedMemberTicketLike(id));
    }

    private MemberTicketLike verifiedMemberTicketLike(Long id) {
        return memberTicketLikeRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOTICE_NOT_FOUND));
    }

}
