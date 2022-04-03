package com.purang.manifest.domain.ticket.repo;

import com.purang.manifest.domain.ticket.entity.TicketDO;

import java.util.List;

/**
 * 票池仓储操作
 */
public interface TicketPoolRepository {

    TicketDO get(String ticketId);

    List<TicketDO> findList(List<String> ticketIdList);

    Boolean batchModifyTicketStatus(List<TicketDO> ticketDoList);
}
