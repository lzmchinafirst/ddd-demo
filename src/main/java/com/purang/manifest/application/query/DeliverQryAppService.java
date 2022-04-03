package com.purang.manifest.application.query;

import com.purang.manifest.adapter.dto.DeliverManifestDetailVo;
import com.purang.manifest.adapter.dto.DeliverManifestQry;
import com.purang.manifest.adapter.dto.DeliverManifestReceiverVo;
import com.purang.manifest.adapter.dto.DeliverManifestVo;
import com.purang.manifest.domain.deliver.entity.Ticket;

import java.util.List;

/**
 * 出库清单查询命令
 */
public interface DeliverQryAppService {
    /**
     * 查询出库清单列表
     *
     * @param deliverManifestQry 台账搜索条件封装
     * @return 出库清单管理台账视图
     */
    List<DeliverManifestVo> queryDeliverManifestList(DeliverManifestQry deliverManifestQry);

    /**
     * 查询出库清单总数
     *
     * @param deliverManifestQry 台账搜索条件封装
     * @return 查询出的出库清单总数
     */
    Integer queryDeliverManifestCount(DeliverManifestQry deliverManifestQry);

    /**
     * 通过出库清单id，获取出库清单的票据列表
     *
     * @param deliverManifestId 出库清单id
     * @return 出库清单的票据视图
     */
    List<Ticket> getTicketListByDeliverManifestId(String deliverManifestId);

    /**
     * 通过出库清单id，获取出库清单的接收人列表
     *
     * @param deliverManifestId 出库清单id
     * @return 出库清单的接收人视图
     */
    List<DeliverManifestReceiverVo> getReceiverListByDeliverManifestId(String deliverManifestId);

    DeliverManifestDetailVo getDeliverManifestDetail(String deliverManifestId);
}
