package project.backend.domain.memberTicketLike.dto;

import lombok.*;
import project.backend.domain.member.dto.MemberResponseDto;
import project.backend.domain.member.entity.Member;
import project.backend.domain.ticket.dto.TicketResponseDto;
import project.backend.domain.ticket.entity.Ticket;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberTicketLikePostRequestDto {
    public Long memberId;
    public Long ticketId;
}