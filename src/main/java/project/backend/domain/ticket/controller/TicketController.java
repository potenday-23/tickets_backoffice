package project.backend.domain.ticket.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.backend.domain.jwt.service.JwtService;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.repository.MemberRepository;
import project.backend.domain.ticket.dto.TicketPatchRequestDto;
import project.backend.domain.ticket.dto.TicketPostRequestDto;
import project.backend.domain.ticket.entity.Ticket;
import project.backend.domain.ticket.mapper.TicketMapper;
import project.backend.domain.ticket.service.TicketService;
import project.backend.domain.ticket.dto.TicketResponseDto;
import project.backend.global.s3.service.ImageService;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
@Api(tags = "티켓 API")
public class TicketController {

    private final TicketService ticketService;
    private final TicketMapper ticketMapper;
    private final ImageService imageService;
    private final JwtService jwtService;
    private final MemberRepository memberRepository;


    @ApiOperation(
            value = "티켓 생성하기",
            notes = " - Header['Authorization'] : AccessToken값 입력\n" +
                    " - image : MultipartFile 입력(사용자가 추가한 이미지)\n" +
                    " - ticketImage : MultipartFile 입력(티켓 완성본 이미지)\n" +
                    " - request : {\n" +
                    "    \"title\" : \"레미제라블\",\n" +
                    "    \"ticketDate\" : \"2023-11-04T16:26:39.098\",\n" +
                    "    \"rating\" : 1,\n" +
                    "    \"memo\" : \"재미없는 공연이였다.\",\n" +
                    "    \"seat\" : \"E292\",\n" +
                    "    \"location\" : \"서울시 서울스퀘어\",\n" +
                    "    \"price\" : 15000,\n" +
                    "    \"friend\" : \"김가영\",\n" +
                    "    \"isPrivate\" : \"PUBLIC\",\n" +
                    "    \"categoryName\" : \"기타\"\n" +
                    "    \"layoutType\" : \"A유형\"\n" +
                    "}\n" +
                    "1. ticketDate는 다음과 같은 형식으로 추가해주세요\n" +
                    "2. isPrivate는 PUBLIC, PRIVATE으로만 추가 가능합니다.\n" +
                    "3. rating은 별점으로, 0 ~ 5까지 소수점으로 입력할 수 있습니다.(n.5 권장)\n" +
                    "4. request는 application/json 형식입니다.\n" +
                    "5. 필수 : 제목, 날짜, 메모, 별점, 이미지2개, 카테고리, 레이아웃 타입")
    @RequestMapping(method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity postTicket(
            @RequestHeader("Authorization") String accessToken,
            @RequestPart(value = "image") MultipartFile image,
            @Valid @RequestPart TicketPostRequestDto request) {

        // image, ticketImage 등록
        request.setImageUrl(imageService.updateImage(image, "Ticket", "imageUrl"));

        // 작성자 등록
        request.setMember(jwtService.getMemberFromAccessToken(accessToken));

        // Ticket 생성
        Ticket ticket = ticketService.createTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketMapper.ticketToTicketResponseDto(ticket));
    }

    @ApiOperation(
            value = "티켓 조회하기 - 내 티켓 혹은 전체 공개 티켓만 조회 가능",
            notes = " - ticketId는 필수\n" +
                    " - Authorization Header는 선택")
    @GetMapping("/{ticketId}")
    public ResponseEntity getTicket(
            @Positive @PathVariable Long ticketId,
            @RequestHeader(value = "Authorization", required = false) String accessToken) {

        Ticket ticket = ticketService.getTicket(ticketId, accessToken);
        TicketResponseDto ticketResponseDto = ticketMapper.ticketToTicketResponseDto(ticket);
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponseDto);
    }

    /**
     * 회원 인증 받지 않아도 조회 가능한 api
     *
     * @return
     */
    @ApiOperation(
            value = "둘러보기 티켓 조회(전체 공개만)",
            notes = " - ?categorys=영화,뮤지컬\n" +
                    " - &period=week    **[week, month, 6month, day로 조회 가능]**\n" +
                    " - &start=2023-11-03\n" +
                    " - &end=2023-11-05\n" +
                    " - &search=레미제라블\n" +
                    " - &mode=mine\n" +
                    "- Header['Authorization'] : 토큰 값\n" +
                    "1. Authorization과 categorys를 입력할 경우, 유저의 온보딩 카테고리보다 categorys로 입력한 카테고리가 필터의 우선순위를 가집니다.\n" +
                    "2. start, end가 period보다 우선순위를 가집니다.\n" +
                    "3. start, end 두 값을 동시에 적지 않으면 filter 기능이 동작하지 않습니다.(에러는 발생하지 않습니다.)\n" +
                    "4. mode=mine을 할 경우 내 티켓만 조회할 수 있습니다.(Authorization토큰이 있어야 동작합니다.)" +
                    "5. 전체 파라미터와 헤더는 필수 값이 아닙니다.")
    @GetMapping
    public ResponseEntity getTicketList(
            @RequestParam(value = "categorys", required = false) List<String> categorys,
            @RequestParam(value = "period", required = false) String period, // 일주일(week), 한달(month), 6개월(6month), 하루(day)
            @RequestParam(value = "start", required = false) String start,
            @RequestParam(value = "end", required = false) String end,
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "mode", required = false) String mode, // mine
            @RequestHeader(value = "Authorization", required = false) String accessToken
    ) {
        if (categorys == null && accessToken != null) {
            categorys = jwtService.getMemberFromAccessToken(accessToken).getOnboardingMemberCategories().stream().map(c -> c.getCategory().getName()).collect(Collectors.toList());
        }

        List<Member> members = new ArrayList<>();
        if (Objects.equals(mode, "mine") && accessToken != null) {
            members.add(jwtService.getMemberFromAccessToken(accessToken));
        } else {
            members = memberRepository.findAll();
        }

        List<Ticket> ticketList = ticketService.getTicketList(categorys, period, start, end, search == null ? "" : search, members);
        List<TicketResponseDto> ticketResponseDtoList = ticketMapper.ticketsToTicketResponseDtos(ticketList);
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponseDtoList);
    }

    @PatchMapping("/{ticketId}")
    public ResponseEntity patchTicket(
            @PathVariable Long ticketId,
            @RequestBody TicketPatchRequestDto ticketPatchRequestDto) {
        TicketResponseDto ticketResponseDto = ticketMapper.ticketToTicketResponseDto(ticketService.patchTicket(ticketId, ticketPatchRequestDto));
        return ResponseEntity.status(HttpStatus.OK).body(ticketResponseDto);
    }

    @ApiOperation(
            value = "티켓 삭제하기(내 티켓만 삭제 가능)",
            notes = "Header의 Authorization 필수")
    @DeleteMapping("/{ticketId}")
    public ResponseEntity deleteTicket(
            @RequestHeader(value = "Authorization") String accessToken,
            @PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId, jwtService.getMemberFromAccessToken(accessToken));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
