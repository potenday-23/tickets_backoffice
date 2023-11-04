package project.backend.domain.ticket.dto;

import lombok.*;
import project.backend.domain.user.entity.SocialType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor // todo : AllArgsConstructor는 무슨 뜻일까
public class TicketResponseDto {
    public String socialId;
}
