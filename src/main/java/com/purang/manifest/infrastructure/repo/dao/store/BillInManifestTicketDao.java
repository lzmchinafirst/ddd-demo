package com.purang.manifest.infrastructure.repo.dao.store;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purang.manifest.infrastructure.repo.po.BillInManifestTicketPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillInManifestTicketDao extends BaseMapper<BillInManifestTicketPO> {
}
