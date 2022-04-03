package com.purang.manifest.infrastructure.repo.dao.store;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purang.manifest.infrastructure.repo.po.BillInStockManifestPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillInStockManifestDao extends BaseMapper<BillInStockManifestPO> {
}
