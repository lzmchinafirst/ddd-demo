package com.purang.manifest.infrastructure.repo.impl.ticket;

import com.purang.manifest.domain.ticket.entity.TicketDO;
import com.purang.manifest.infrastructure.repo.po.BillTicketPoolPO;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface TicketPoConverter {
    TicketPoConverter INSTANCE = getMapper(TicketPoConverter.class);

    TicketDO toTicketDO(BillTicketPoolPO ticketPO);

    List<TicketDO> toTicketDos(List<BillTicketPoolPO> ticketPos);

    BillTicketPoolPO toTicketPO(TicketDO ticketDO);

    List<BillTicketPoolPO> toTicketPos(List<TicketDO> ticketDos);
}
