package project.backend.domain.ticket.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.domain.common.entity.BaseEntity;
import project.backend.domain.ticket.dto.TicketPatchRequestDto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Ticket extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // todo : IDENTITY와 AUTO 차이점이 뭔지?
    @Column(name = "ticket_id")
    public Long id;
    @Column(name = "imageUrl")
    public String imageUrl;

    @Column(name = "ticketImageUrl")
    public String ticketImageUrl;

    @Column(name = "ticketDate")
    public LocalDateTime ticketDate;

    @Column(name = "rating")
    public Integer rating;

    @Column(name = "memo")
    public String memo;

    @Column(name = "seat")
    public String seat;

    @Column(name = "location")
    public String location;

    @Column(name = "price")
    public Integer price;

    @Column(name = "friend")
    public String friend;

    @Column(name = "isPrivate")
    public IsPrivate isPrivate;


    @Builder
    public Ticket(String imageUrl, String ticketImageUrl, LocalDateTime ticketDate, Integer rating, String memo, String seat,
                  String location, Integer price, String friend, IsPrivate isPrivate) {
        this.imageUrl = imageUrl;
        this.ticketImageUrl = ticketImageUrl;
        this.ticketDate = ticketDate;
        this.rating = rating;
        this.memo = memo;
        this.seat = seat;
        this.location = location;
        this.price = price;
        this.friend = friend;
        this.isPrivate = isPrivate;
    }

    // Patch
    public Ticket patchTicket(TicketPatchRequestDto ticketPatchRequestDto) {
        this.imageUrl = Optional.ofNullable(ticketPatchRequestDto.getImageUrl()).orElse(this.imageUrl);
        this.ticketImageUrl = Optional.ofNullable(ticketPatchRequestDto.getTicketImageUrl()).orElse(this.ticketImageUrl);
        this.ticketDate = Optional.ofNullable(ticketPatchRequestDto.getTicketDate()).orElse(this.ticketDate);
        this.rating = (rating != 0) ? ticketPatchRequestDto.getRating() : this.rating;
        this.memo = Optional.ofNullable(ticketPatchRequestDto.getMemo()).orElse(this.memo);
        this.seat = Optional.ofNullable(ticketPatchRequestDto.getSeat()).orElse(this.seat);
        this.location = Optional.ofNullable(ticketPatchRequestDto.getLocation()).orElse(this.location);
        this.price = (price != 0) ? ticketPatchRequestDto.getPrice() : this.price;
        this.friend = Optional.ofNullable(ticketPatchRequestDto.getFriend()).orElse(this.friend);
        this.isPrivate = Optional.ofNullable(ticketPatchRequestDto.getIsPrivate()).orElse(this.isPrivate);
        return this;
    }
}
