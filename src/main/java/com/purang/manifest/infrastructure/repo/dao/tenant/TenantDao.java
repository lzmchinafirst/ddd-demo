package com.purang.manifest.infrastructure.repo.dao.tenant;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purang.manifest.infrastructure.repo.po.BillTenantPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TenantDao extends BaseMapper<BillTenantPO> {
}
