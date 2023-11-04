package project.backend.domain.ticket.dto;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor // todo : AllArgsConstructor는 무슨 뜻일까
public class TicketPatchRequestDto {
    public String socialId;
}
