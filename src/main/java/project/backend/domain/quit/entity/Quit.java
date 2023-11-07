package project.backend.domain.quit.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import project.backend.domain.common.entity.BaseEntity;
import project.backend.domain.quit.dto.QuitPatchRequestDto;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class Quit extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    public Long id;

    @Column(name = "title")
    public String reason;

    @Column(name = "content")
    public Integer count;

    @Builder
    public Quit(String reason){
        this.reason = reason;
        this.count = 0;
    }

    // Patch
    public Quit patchQuit(QuitPatchRequestDto quitPatchRequestDto){
        this.reason = Optional.ofNullable(quitPatchRequestDto.getReason()).orElse(this.reason);
        this.count = Optional.ofNullable(quitPatchRequestDto.getCount()).orElse(this.count);
        return this;
    }
}
