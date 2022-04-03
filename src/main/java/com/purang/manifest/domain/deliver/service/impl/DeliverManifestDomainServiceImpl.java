package com.purang.manifest.domain.deliver.service.impl;

import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;
import com.purang.manifest.domain.deliver.service.DeliverManifestDomainService;
import com.purang.manifest.domain.trader.entity.TraderDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 出库清单领域服务
 */
@Slf4j
@Service
public class DeliverManifestDomainServiceImpl implements DeliverManifestDomainService {
    /**
     * 出库清单 - 合并
     *
     * @param deliverManifestDo1 被合并的出库清单1
     * @param deliverManifestDo2 被合并的出库清单2
     * @param mainTrader         合并后的主对接人
     */
    @Override
    public void merge(DeliverManifestDO deliverManifestDo1, DeliverManifestDO deliverManifestDo2, TraderDO mainTrader) {

    }
}
