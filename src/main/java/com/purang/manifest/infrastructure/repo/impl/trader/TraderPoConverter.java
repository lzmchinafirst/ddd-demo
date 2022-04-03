package com.purang.manifest.infrastructure.repo.impl.trader;

import com.purang.manifest.domain.trader.entity.TraderDO;
import com.purang.manifest.infrastructure.repo.po.BillTraderPO;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface TraderPoConverter {
    TraderPoConverter INSTANCE = getMapper(TraderPoConverter.class);

    TraderDO toTraderDO(BillTraderPO traderPO);

    List<TraderDO> toTraderDos(List<BillTraderPO> traderPos);
}
