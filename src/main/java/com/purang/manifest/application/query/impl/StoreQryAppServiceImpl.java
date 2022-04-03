package com.purang.manifest.application.query.impl;

import com.purang.manifest.adapter.dto.DeliverManifestQry;
import com.purang.manifest.adapter.dto.DeliverManifestReceiverVo;
import com.purang.manifest.adapter.dto.DeliverManifestTicketVo;
import com.purang.manifest.adapter.dto.DeliverManifestVo;
import com.purang.manifest.application.query.StoreQryAppService;
import com.purang.manifest.domain.store.repo.StoreManifestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 查询应用层，一般性关联表查询台账，建议直接调用仓储层
 */
@Slf4j
@Service
public class StoreQryAppServiceImpl implements StoreQryAppService {

    private StoreManifestRepository storeManifestRepository;

    @Autowired
    public StoreQryAppServiceImpl(StoreManifestRepository storeManifestRepository) {
        this.storeManifestRepository = storeManifestRepository;
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
    public List<DeliverManifestTicketVo> getTicketListByDeliverManifestId(String deliverManifestId) {
        return null;
    }

    @Override
    public List<DeliverManifestReceiverVo> getReceiverListByDeliverManifestId(String deliverManifestId) {
        return null;
    }
}
