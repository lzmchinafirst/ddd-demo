package com.purang.manifest.infrastructure.repo.dao.deliver;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purang.manifest.adapter.dto.DeliverManifestDetailVo;
import com.purang.manifest.infrastructure.repo.po.BillOutStockManifestPO;
import com.purang.manifest.infrastructure.repo.po.BillTicketPoolPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用于出库清单、票据等关联信息联合查询
 */
@Mapper
public interface DeliverManifestInfoDao extends BaseMapper<BillOutStockManifestPO> {
    List<BillTicketPoolPO> selectTicketsByDeliverManifestId(String deliverManifestId);

    DeliverManifestDetailVo selectDeliverManifestDetail(String deliverManifestId);
}
