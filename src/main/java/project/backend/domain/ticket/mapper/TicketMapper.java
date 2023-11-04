package project.backend.domain.ticket.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import project.backend.domain.ticket.dto.TicketPatchRequestDto;
import project.backend.domain.ticket.dto.TicketPostRequestDto;
import project.backend.domain.ticket.entity.Ticket;
import project.backend.domain.ticket.dto.TicketResponseDto;

import java.util.List;

// todo: ReportingPolicy.IGNORE은 무슨 뜻일까?
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TicketMapper {
    Ticket ticketPostRequestDtoToTicket(TicketPostRequestDto ticketPostRequestDto);

    Ticket ticketPatchRequestDtoToTicket(TicketPatchRequestDto ticketPatchRequestDto);

    TicketResponseDto ticketToTicketResponseDto(Ticket ticket);

    List<TicketResponseDto> ticketsToTicketResponseDtos(List<Ticket> ticket);
}
