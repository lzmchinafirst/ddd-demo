package com.purang.manifest.application.command;

import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;

import java.util.List;

/**
 * 清单出库命令应用服务
 */
public interface DeliverCmdAppService {
    /**
     * 创建出库清单
     *
     * @param deliverManifestDO  保存出库清单领域数据
     * @param senderUserId       出库清单-发送方userId
     * @param ticketIdList       出库清单-票列表
     * @param receiverUserIdList 出库清单-接收方userId列表
     * @return
     */
    DeliverManifestDO createDeliverManifest(DeliverManifestDO deliverManifestDO, String senderUserId,
                                            List<String> ticketIdList, List<String> receiverUserIdList);

    /**
     * 更新出库清单
     *
     * @param deliverManifestDO  保存出库清单领域数据
     * @param senderUserId       出库清单-发送方userId
     * @param ticketIdList       出库清单-票列表
     * @param receiverUserIdList 出库清单-接收方userId列表
     * @return
     */
    DeliverManifestDO updateDeliverManifest(DeliverManifestDO deliverManifestDO, String senderUserId,
                                            List<String> ticketIdList, List<String> receiverUserIdList);


    /**
     * 合并出库清单
     *
     * @param deliverManifestDo1
     * @param deliverManifestDo2
     * @param mainTrader
     * @return
     */
    Boolean merge(String deliverManifestId1, String deliverManifestId2, String senderUserId);

    /**
     * 发送出库清单
     *
     * @param deliverManifestId 将发送的出库清单的id
     */
    Boolean sendDeliverManifest(String deliverManifestId);

    /**
     * 确认收款
     *
     * @param deliverManifestId 出库清单id
     * @param receiverUserId    确认收款接收方userId
     * @return
     */
    Boolean confirmReceipt(String deliverManifestId, String receiverUserId);
}
