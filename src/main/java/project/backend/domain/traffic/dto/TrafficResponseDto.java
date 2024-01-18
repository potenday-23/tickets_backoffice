package project.backend.domain.traffic.dto;
import lombok.*;
import project.backend.domain.member.dto.MemberResponseDto;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrafficResponseDto {
    public Long id;
    public String buttonName;
    public MemberResponseDto member;
    public LocalDateTime createdDate;
    public LocalDateTime updatedDate;
}