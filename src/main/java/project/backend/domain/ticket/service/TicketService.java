package project.backend.domain.ticket.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.backend.domain.category.service.CategoryService;
import project.backend.domain.jwt.service.JwtService;
import project.backend.domain.member.entity.Member;
import project.backend.domain.member.entity.SocialType;
import project.backend.domain.ticket.dto.TicketPatchRequestDto;
import project.backend.domain.ticket.dto.TicketPostRequestDto;
import project.backend.domain.ticket.entity.IsPrivate;
import project.backend.domain.ticket.entity.Ticket;
import project.backend.domain.ticket.mapper.TicketMapper;
import project.backend.domain.ticket.repository.TicketRepository;
import project.backend.global.error.exception.BusinessException;
import project.backend.global.error.exception.ErrorCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;
    private final CategoryService categoryService;
    private final JwtService jwtService;

    public Ticket createTicket(TicketPostRequestDto ticketPostRequestDto) {
        Ticket ticket = Ticket.builder()
                .title(ticketPostRequestDto.title)
                .imageUrl(ticketPostRequestDto.imageUrl)
                .ticketDate(ticketPostRequestDto.ticketLocalDateTime)
                .rating(ticketPostRequestDto.rating)
                .memo(ticketPostRequestDto.memo)
                .seat(ticketPostRequestDto.seat)
                .location(ticketPostRequestDto.location)
                .price(ticketPostRequestDto.price)
                .friend(ticketPostRequestDto.friend)
                .color(ticketPostRequestDto.color)
                .isPrivate(ticketPostRequestDto.isPrivate)
                .ticketType(ticketPostRequestDto.ticketType)
                .layoutType(ticketPostRequestDto.layoutType)
                .build();

        // 연관관계 매핑
        ticket.setMember(ticketPostRequestDto.member);
        ticket.setCategory(categoryService.verifiedCategory(ticketPostRequestDto.categoryName));

        // 저장
        ticketRepository.save(ticket);
        return ticket;
    }

    /**
     * 내 티켓 또는 전체공개 티켓만 조회 가능
     * @param id
     * @param accessToken
     * @return
     */
    public Ticket getTicket(Long id, String accessToken) {
        Ticket ticket = verifiedTicket(id);
        if (ticket.isPrivate == IsPrivate.PUBLIC) {
            return ticket;
        } else if (accessToken!=null) {
            if (ticket.member == jwtService.getMemberFromAccessToken(accessToken)) {
                return ticket;
            }
        }
        throw new BusinessException(ErrorCode.TICKET_VIEW_FAIL);
    }

    public List<Ticket> getTotalTicketList(List<String> categorys, String period, String start, String end, String search) {
        return ticketRepository.getTotalTicketList(categorys, getStartAndEnd(period, start, end), search);
    }

    public List<Ticket> getTotalAndMyTicketList(List<String> categorys, String period, String start, String end, String search, Member member) {
        return ticketRepository.getTotalAndMyTicketList(categorys, getStartAndEnd(period, start, end), search, member);
    }

    public List<Ticket> getMyTicketList(List<String> categorys, String period, String start, String end, String search, Member member) {
        return ticketRepository.getMyTicketList(categorys, getStartAndEnd(period, start, end), search, member);
    }

    public Ticket patchTicket(Long id, TicketPatchRequestDto ticketPatchRequestDto, String accessToken) {

        Ticket ticket = verifiedTicket(id);
        if (ticket.member == jwtService.getMemberFromAccessToken(accessToken)) {
            ticket.patchTicket(ticketPatchRequestDto);
            return ticketRepository.save(ticket);
        }
        throw new BusinessException(ErrorCode.TICKET_PATCH_FAIL);
    }

    public void deleteTicket(Long id, Member member) {
        Ticket ticket = verifiedTicket(id);
        if (ticket.member == member) {
            ticketRepository.delete(ticket);
        } else {
            throw new BusinessException(ErrorCode.TICKET_DELETE_FAIL);
        }
    }

    public Ticket verifiedTicket(Long id) {
        return ticketRepository.findById(id).orElseThrow(() -> new BusinessException(ErrorCode.TICKET_NOT_FOUND));
    }

    private List<LocalDateTime> getStartAndEnd(String period, String start, String end) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<LocalDateTime> startAndEndList = new ArrayList<>();

        if (start != null && end!= null) {
            startAndEndList.add(LocalDate.parse(start, dateTimeFormatter).atStartOfDay());
            startAndEndList.add(LocalDate.parse(end, dateTimeFormatter).atTime(LocalTime.MAX));
        } else if (Objects.equals(period, "week")) {
            startAndEndList.add(LocalDate.now().minusWeeks(1).atStartOfDay());
            startAndEndList.add(LocalDate.now().atTime(LocalTime.MAX));
        } else if (Objects.equals(period, "month")) {
            startAndEndList.add(LocalDate.now().minusMonths(1).atStartOfDay());
            startAndEndList.add(LocalDate.now().atTime(LocalTime.MAX));
        } else if (Objects.equals(period, "6month")) {
            startAndEndList.add(LocalDate.now().minusMonths(6).atStartOfDay());
            startAndEndList.add(LocalDate.now().atTime(LocalTime.MAX));
        } else if (Objects.equals(period, "day")) {
            startAndEndList.add(LocalDate.now().atStartOfDay());
            startAndEndList.add(LocalDate.now().atTime(LocalTime.MAX));
        } else {
            startAndEndList.add(LocalDate.now().minusYears(100).atStartOfDay());
            startAndEndList.add(LocalDate.now().atTime(LocalTime.MAX));
        }
        return startAndEndList;
    }

}
