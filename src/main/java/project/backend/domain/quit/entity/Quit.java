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
    public String title;

    @Column(name = "content")
    public String content;

    @Builder
    public Quit(String title, String content){
        this.title = title;
        this.content = content;
    }

    // Patch
    public Quit patchQuit(QuitPatchRequestDto quitPatchRequestDto){
        this.title = Optional.ofNullable(quitPatchRequestDto.getTitle()).orElse(this.title);
        this.content = Optional.ofNullable(quitPatchRequestDto.getContent()).orElse(this.content);
        return this;
    }
}
