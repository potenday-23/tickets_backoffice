package project.backend.domain.ticket.dto;

import lombok.*;
import project.backend.domain.member.entity.Member;
import project.backend.domain.ticket.entity.IsPrivate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketStatusPostRequestDto {
    private IsPrivate isPrivate;
}
