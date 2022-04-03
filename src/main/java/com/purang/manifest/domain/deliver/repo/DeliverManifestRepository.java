package com.purang.manifest.domain.deliver.repo;

import com.purang.manifest.adapter.dto.DeliverManifestDetailVo;
import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;
import com.purang.manifest.domain.deliver.entity.Ticket;
import com.purang.manifest.domain.deliver.entity.Trader;
import com.purang.manifest.domain.deliver.entity.dp.BusinessNoDP;

import java.util.List;

public interface DeliverManifestRepository {
    /**
     * 保存出库清单
     *
     * @return 出库清单领域对象
     */
    Integer saveDeliverManifest(DeliverManifestDO deliverManifestDO);

    /**
     * 通过出库清单id，获取出库清单领域对象
     *
     * @param deliverManifestId 出库清单id
     * @return 出库清单领域对象
     */
    DeliverManifestDO getDeliverManifest(String deliverManifestId);

    /**
     * 查取今天下一个要生成的出库清单编号
     *
     * @return 最新出库清单编号
     */
    BusinessNoDP findNextDeliverManifestBusinessNo();

    /**
     * 通过出库清单id，查取出库清单接收人列表
     *
     * @param deliverManifestId 出库清单id
     * @return 对接人
     */
    List<Trader> findReceiversByDeliverManifestId(String deliverManifestId);

    /**
     * 通过出库清单id，查取出库清单票据列表
     *
     * @param deliverManifestId 出库清单id
     * @return 票据列表
     */
    List<Ticket> findTicketsByDeliverManifestId(String deliverManifestId);

    /**
     * 通过出库清单id，查取出库清单详情
     *
     * @param deliverManifestId
     * @return
     */
    DeliverManifestDetailVo getDeliverManifestDetail(String deliverManifestId);
}