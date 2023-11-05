package project.backend.domain.ticket.dto;

import lombok.*;
import project.backend.domain.ticket.entity.IsPrivate;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor // todo : AllArgsConstructor는 무슨 뜻일까
public class TicketPatchRequestDto {
    public String imageUrl;
    public String ticketImageUrl;
    public LocalDateTime ticketDate;
    public Float rating;
    public String memo;
    public String seat;
    public String location;
    public Integer price;
    public String friend;
    public IsPrivate isPrivate;
}
