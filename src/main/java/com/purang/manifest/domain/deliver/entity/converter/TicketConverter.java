package com.purang.manifest.domain.deliver.entity.converter;

import com.purang.manifest.domain.deliver.entity.Ticket;
import com.purang.manifest.domain.ticket.entity.TicketDO;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface TicketConverter {
    TicketConverter INSTANCE = getMapper(TicketConverter.class);

    Ticket toTicket(TicketDO ticketDO);

    List<Ticket> toTickets(List<TicketDO> ticketDoList);

    TicketDO toTicketDO(Ticket ticket);

    List<TicketDO> toTicketDos(List<Ticket> ticketList);
}
