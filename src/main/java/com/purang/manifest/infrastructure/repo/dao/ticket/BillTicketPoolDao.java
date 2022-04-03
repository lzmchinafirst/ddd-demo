package com.purang.manifest.infrastructure.repo.dao.ticket;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purang.manifest.infrastructure.repo.po.BillTicketPoolPO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BillTicketPoolDao extends BaseMapper<BillTicketPoolPO> {
}
