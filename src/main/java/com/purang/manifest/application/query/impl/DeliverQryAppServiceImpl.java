package com.purang.manifest.application.query.impl;

import com.purang.manifest.adapter.dto.DeliverManifestDetailVo;
import com.purang.manifest.adapter.dto.DeliverManifestQry;
import com.purang.manifest.adapter.dto.DeliverManifestReceiverVo;
import com.purang.manifest.adapter.dto.DeliverManifestVo;
import com.purang.manifest.application.query.DeliverQryAppService;
import com.purang.manifest.domain.deliver.entity.Ticket;
import com.purang.manifest.domain.deliver.repo.DeliverManifestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询应用层，一般性关联表查询台账，建议直接调用仓储层
 */
@Slf4j
@Service
public class DeliverQryAppServiceImpl implements DeliverQryAppService {

    private final DeliverManifestRepository deliverManifestRepository;

    @Autowired
    public DeliverQryAppServiceImpl(DeliverManifestRepository deliverManifestRepository) {
        this.deliverManifestRepository = deliverManifestRepository;
    }

    @Override
    public List<DeliverManifestVo> queryDeliverManifestList(DeliverManifestQry deliverManifestQry) {
        return null;
    }

    @Override
    public Integer queryDeliverManifestCount(DeliverManifestQry deliverManifestQry) {
        return null;
    }

    @Override
    public List<Ticket> getTicketListByDeliverManifestId(String deliverManifestId) {
        return deliverManifestRepository.findTicketsByDeliverManifestId(deliverManifestId);
    }

    @Override
    public List<DeliverManifestReceiverVo> getReceiverListByDeliverManifestId(String deliverManifestId) {
        return null;
    }

    @Override
    public DeliverManifestDetailVo getDeliverManifestDetail(String deliverManifestId) {
        return deliverManifestRepository.getDeliverManifestDetail(deliverManifestId);
    }
}
