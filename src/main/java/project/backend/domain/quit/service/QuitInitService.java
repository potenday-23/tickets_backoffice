package project.backend.domain.quit.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import project.backend.domain.category.entity.Category;
import project.backend.domain.category.repository.CategoryRepository;
import project.backend.domain.quit.entity.Quit;
import project.backend.domain.quit.repository.QuitRepository;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class QuitInitService implements ApplicationRunner {

    private final QuitRepository quitRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(quitRepository.findAll().size() == 0) {
            List<Quit> quitList = new ArrayList<>();

            quitList.add(Quit.builder().reason("만들고 싶은 티켓이 없어요").build());
            quitList.add(Quit.builder().reason("티켓 커스텀 기능이 아쉬워요").build());
            quitList.add(Quit.builder().reason("더 좋은 어플을 찾았어요").build());
            quitList.add(Quit.builder().reason("계정을 새로 만들고 싶어요").build());
            quitList.add(Quit.builder().reason("기타").build());

            quitRepository.saveAll(quitList);
        }
    }
}