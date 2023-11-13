package project.backend.domain.member.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberYearStatisticsResponseDto {
    private int year;
    private int month;
    private int count;
}