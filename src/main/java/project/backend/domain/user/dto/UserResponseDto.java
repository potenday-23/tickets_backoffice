package project.backend.domain.user.dto;

import lombok.*;
import project.backend.domain.user.entity.SocialType;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor // todo : AllArgsConstructor는 무슨 뜻일까
public class UserResponseDto {
    public Long id;
    public SocialType socialType;
    public String socialId;
}
