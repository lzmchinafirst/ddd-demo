package com.purang.manifest.domain.trader.repo;

import com.purang.manifest.adapter.dto.TraderTenantVo;
import com.purang.manifest.domain.trader.entity.TraderDO;

import java.util.List;

public interface TraderRepository {

    TraderDO get(String traderId);

    List<TraderDO> findList(List<String> traderIdList);

    TraderTenantVo getTraderTenant(String traderId);

    List<TraderTenantVo> findTraderTenantList(List<String> traderIdList);
}
