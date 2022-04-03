package com.purang.manifest.domain.deliver.service;

import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;
import com.purang.manifest.domain.trader.entity.TraderDO;

/**
 * 出库清单领域服务
 * 1、计算
 */
public interface DeliverManifestDomainService {

    /**
     * 清单合并
     *
     * @param deliverManifestDO
     */
    void merge(DeliverManifestDO deliverManifestDo1, DeliverManifestDO deliverManifestDo2, TraderDO mainTrader);
}
