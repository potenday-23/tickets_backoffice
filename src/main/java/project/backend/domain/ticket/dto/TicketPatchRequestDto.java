package project.backend.domain.ticket.dto;

import lombok.*;
import project.backend.domain.member.entity.Member;
import project.backend.domain.ticket.entity.IsPrivate;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TicketPatchRequestDto {
    @NotNull(message = "제목을 입력해야 합니다.")
    public String title;
    public String imageUrl;
    @NotNull(message = "날짜를 입력해야 합니다.")
    public LocalDateTime ticketDate;
    @Min(value = 0, message = "0보다 작은 값은 별점으로 설정할 수 없습니다.")
    @Max(value = 5, message = "5보다 큰 값은 별점으로 설정할 수 없습니다.")
    public Float rating;
    public String memo;
    public String seat;
    public String location;
    @Min(value = 0, message = "0보다 작은 가격은 설정할 수 없습니다.")
    public Integer price;
    public String friend;
    public IsPrivate isPrivate;
    @NotNull(message = "카테고리명을 선택해야 합니다.")
    public String categoryName;
    @NotNull(message = "레이아웃 타입을 선택해야 합니다.")
    public String layoutType;
}
