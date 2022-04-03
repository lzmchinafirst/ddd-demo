package com.purang.manifest.infrastructure.repo.dao.trader;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purang.manifest.infrastructure.repo.po.BillTraderPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TraderDao extends BaseMapper<BillTraderPO> {
}
