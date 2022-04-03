package com.purang.manifest.infrastructure.repo.impl.ticket;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.purang.manifest.domain.ticket.entity.TicketDO;
import com.purang.manifest.domain.ticket.repo.TicketPoolRepository;
import com.purang.manifest.infrastructure.repo.dao.ticket.BillTicketPoolDao;
import com.purang.manifest.infrastructure.repo.po.BillTicketPoolPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 票池仓储操作
 */
@Repository
public class TicketPoolRepositoryImpl extends ServiceImpl<BillTicketPoolDao, BillTicketPoolPO> implements TicketPoolRepository {

    private final BillTicketPoolDao ticketPoolDao;

    @Autowired
    public TicketPoolRepositoryImpl(BillTicketPoolDao ticketPoolDao) {
        this.ticketPoolDao = ticketPoolDao;
    }

    @Override
    public TicketDO get(String ticketId) {
        BillTicketPoolPO ticketPoolPO = ticketPoolDao.selectById(ticketId);
        return TicketPoConverter.INSTANCE.toTicketDO(ticketPoolPO);
    }

    @Override
    public List<TicketDO> findList(List<String> ticketIdList) {
        List<BillTicketPoolPO> ticketList = ticketPoolDao.selectBatchIds(ticketIdList);
        return TicketPoConverter.INSTANCE.toTicketDos(ticketList);
    }

    @Override
    public Boolean batchModifyTicketStatus(List<TicketDO> ticketDoList) {
        List<BillTicketPoolPO> ticketPoolPoList = TicketPoConverter.INSTANCE.toTicketPos(ticketDoList);
        return updateBatchById(ticketPoolPoList);
    }
}
