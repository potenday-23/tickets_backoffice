package project.backend.domain.ticket.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class TicketPatchRequestDto {
    public String title;
    public String imageUrl;
    public String ticketDate;
    @Min(value = 0, message = "0보다 작은 값은 별점으로 설정할 수 없습니다.")
    @Max(value = 5, message = "5보다 큰 값은 별점으로 설정할 수 없습니다.")
    public Float rating;
    @Size(max=10, message = "100자 이내로 입력해주세요.")
    public String memo;
    public String seat;
    public String location;
    @Min(value = 0, message = "0보다 작은 가격은 설정할 수 없습니다.")
    public Integer price;
    public String friend;
    public String color;
    public IsPrivate isPrivate;
    public String categoryName;
    public String ticketType;
    public String layoutType;
    public LocalDateTime ticketLocalDateTime;
}
