package com.purang.manifest.application.command.impl;

import com.purang.manifest.application.command.StoreCmdAppService;
import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;
import com.purang.manifest.domain.deliver.repo.DeliverManifestRepository;
import com.purang.manifest.domain.store.entity.StoreManifestDO;
import com.purang.manifest.domain.ticket.repo.TicketPoolRepository;
import com.purang.manifest.domain.trader.repo.TraderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * 命令应用层负责领域的编排、事务的处理，领域对象的存储
 */
@Slf4j
@Component
public class StoreCmdAppServiceImpl implements StoreCmdAppService {
    private final DeliverManifestRepository deliverManifestRepository;

    private final TicketPoolRepository ticketPoolRepository;

    private final TraderRepository traderRepository;

    @Autowired
    public StoreCmdAppServiceImpl(DeliverManifestRepository deliverManifestRepository,
                                  TicketPoolRepository ticketPoolRepository,
                                  TraderRepository traderRepository) {
        this.deliverManifestRepository = deliverManifestRepository;
        this.ticketPoolRepository = ticketPoolRepository;
        this.traderRepository = traderRepository;
    }

    @Override
    public StoreManifestDO createStoreManifest(String deliverManifestId) {
        DeliverManifestDO deliverManifestDO = deliverManifestRepository.getDeliverManifest(deliverManifestId);
        deliverManifestDO.setTicketList(deliverManifestRepository.findTicketsByDeliverManifestId(deliverManifestId));
        deliverManifestDO.setReceiverTraderList(deliverManifestRepository.findReceiversByDeliverManifestId(deliverManifestId));
        return null;
    }

    @Override
    public Boolean replyPrice(String deliverManifestId, BigDecimal replyPrice) {
        return null;
    }

    @Override
    public Boolean acceptPrice(String deliverManifestId) {
        return null;
    }
}
