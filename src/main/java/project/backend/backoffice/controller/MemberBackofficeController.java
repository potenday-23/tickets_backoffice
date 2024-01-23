// package
package project.backend.backoffice.controller;

// Modules
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.repository.MemberRepository;
import project.backend.domain.member.service.MemberService;
import project.backend.global.s3.service.ImageService;

// Java
import java.util.List;


// Main Section
@Controller
@EnableWebMvc
@RequestMapping("/backoffice/member")
@RequiredArgsConstructor
public class MemberBackofficeController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final ImageService imageService;

    /**
     * READ : 멤버 확인
     *
     * @param model
     * @return
     */
    @GetMapping
    public String member(Model model) {
        List<Member> members = memberService.getMemberList();
        model.addAttribute("members", members);
        return "member/member";
    }

}
