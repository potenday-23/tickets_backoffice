package project.backend.domain.ticket.dto;

import lombok.*;
import project.backend.domain.category.dto.CategoryResponseDto;
import project.backend.domain.member.dto.MemberResponseDto;
import project.backend.domain.ticket.entity.IsPrivate;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketResponseDto {
    public Long id;
    public String title;
    public String imageUrl;
    public LocalDateTime ticketDate;
    public Float rating;
    public String memo;
    public String seat;
    public String location;
    public Integer price;
    public String friend;
    public String ticketType;
    public String layoutType;
    public IsPrivate isPrivate;
    public CategoryResponseDto category;
    public MemberResponseDto member;
}
