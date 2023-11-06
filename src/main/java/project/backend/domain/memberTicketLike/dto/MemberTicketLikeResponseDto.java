package project.backend.domain.memberTicketLike.dto;
import lombok.*;
import project.backend.domain.member.dto.MemberResponseDto;
import project.backend.domain.ticket.dto.TicketResponseDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberTicketLikeResponseDto {
    public MemberResponseDto member;
    public TicketResponseDto ticket;
    public LocalDateTime createdDate;
    public LocalDateTime updatedDate;
}