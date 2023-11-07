package project.backend.domain.quit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.quit.dto.QuitPatchRequestDto;
import project.backend.domain.quit.dto.QuitPostRequestDto;
import project.backend.domain.quit.entity.Quit;
import project.backend.domain.quit.mapper.QuitMapper;
import project.backend.domain.quit.repository.QuitRepository;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class QuitService {
    private final QuitRepository quitRepository;
    private final QuitMapper quitMapper;

    public Quit createQuit(QuitPostRequestDto quitPostRequestDto){
        Quit quit = Quit.builder()
                .title(quitPostRequestDto.getTitle())
                .content(quitPostRequestDto.getContent()).build();
        quitRepository.save(quit);
        return quit;
    }

    public Quit getQuit(Long id) {
        return verifiedQuit(id);
    }

    public List<Quit> getQuitList() {
        return quitRepository.findAll();
    }

    public Quit patchQuit(Long id, QuitPatchRequestDto quitPatchRequestDto) {
        Quit quit = verifiedQuit(id).patchQuit(quitPatchRequestDto);
        quitRepository.save(quit);
        return quit;
    }

    public void deleteQuit(Long id) {
        quitRepository.delete(verifiedQuit(id));
    }

    private Quit verifiedQuit(Long id) {
        return quitRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.NOTICE_NOT_FOUND));
    }

}
