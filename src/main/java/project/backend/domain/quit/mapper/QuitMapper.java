package project.backend.domain.quit.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.backend.domain.quit.dto.QuitPatchRequestDto;
import project.backend.domain.quit.dto.QuitPostRequestDto;
import project.backend.domain.quit.dto.QuitResponseDto;
import project.backend.domain.quit.entity.Quit;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuitMapper {
    Quit quitPostRequestDtoToQuit(QuitPostRequestDto quitPostRequestDto);

    Quit quitPatchRequestDtoToQuit(QuitPatchRequestDto quitPatchRequestDto);

    QuitResponseDto quitToQuitResponseDto(Quit quit);

    List<QuitResponseDto> quitsToQuitResponseDtos(List<Quit> quit);
}
