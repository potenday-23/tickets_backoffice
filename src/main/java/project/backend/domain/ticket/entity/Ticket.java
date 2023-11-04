package project.backend.domain.ticket.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.domain.common.entity.BaseEntity;
import project.backend.domain.ticket.dto.TicketPatchRequestDto;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // todo : IDENTITY와 AUTO 차이점이 뭔지?
    @Column(name = "ticket_id")
    public Long id;

    @Column(name = "social_id")
    public String socialId;

    @Builder
    public Ticket(String socialId){
        this.socialId = socialId;
    }

    // Patch
    public Ticket patchTicket(TicketPatchRequestDto ticketPatchRequestDto){
        this.socialId = Optional.ofNullable(ticketPatchRequestDto.getSocialId()).orElse(this.socialId);
        return this;
    }
}
