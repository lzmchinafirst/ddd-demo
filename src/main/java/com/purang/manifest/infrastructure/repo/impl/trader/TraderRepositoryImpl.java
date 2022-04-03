package com.purang.manifest.infrastructure.repo.impl.trader;

import com.purang.manifest.adapter.dto.TraderTenantVo;
import com.purang.manifest.domain.trader.entity.TraderDO;
import com.purang.manifest.domain.trader.repo.TraderRepository;
import com.purang.manifest.infrastructure.repo.dao.trader.TraderDao;
import com.purang.manifest.infrastructure.repo.dao.trader.TraderTenantDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TraderRepositoryImpl implements TraderRepository {

    private final TraderDao traderDao;

    private final TraderTenantDao traderTenantDao;

    @Autowired
    public TraderRepositoryImpl(TraderDao traderDao, TraderTenantDao traderTenantDao) {
        this.traderDao = traderDao;
        this.traderTenantDao = traderTenantDao;
    }

    @Override
    public TraderDO get(String traderId) {
        return TraderPoConverter.INSTANCE.toTraderDO((traderDao.selectById(traderId)));
    }

    @Override
    public List<TraderDO> findList(List<String> traderIdList) {
        return TraderPoConverter.INSTANCE.toTraderDos(traderDao.selectBatchIds(traderIdList));
    }

    @Override
    public TraderTenantVo getTraderTenant(String traderId) {
        List<TraderTenantVo> list = findTraderTenantList(new ArrayList<String>() {{
            add(traderId);
        }});
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<TraderTenantVo> findTraderTenantList(List<String> traderIdList) {
        return traderTenantDao.selectTraderTenantListByTraderIdList(traderIdList);
    }
}
