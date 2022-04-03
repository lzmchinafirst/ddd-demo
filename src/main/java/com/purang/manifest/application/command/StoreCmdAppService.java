package com.purang.manifest.application.command;

import com.purang.manifest.domain.store.entity.StoreManifestDO;

import java.math.BigDecimal;

/**
 * 清单入库命令应用服务
 */
public interface StoreCmdAppService {
    /**
     * 创建入库清单
     *
     * @param deliverManifestId 出库清单id
     * @return 入库清单实体
     */
    StoreManifestDO createStoreManifest(String deliverManifestId);


    /**
     * 回复报价
     *
     * @param deliverManifestId 回复的出库清单id
     * @param replyPrice        回复的价格
     * @return
     */
    Boolean replyPrice(String deliverManifestId, BigDecimal replyPrice);

    /**
     * 接受价格
     *
     * @param deliverManifestId 接收的出库清单id
     */
    Boolean acceptPrice(String deliverManifestId);
}
