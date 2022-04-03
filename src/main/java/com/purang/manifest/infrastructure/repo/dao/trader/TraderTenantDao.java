package com.purang.manifest.infrastructure.repo.dao.trader;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.purang.manifest.adapter.dto.TraderTenantVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TraderTenantDao extends BaseMapper<TraderTenantVo> {
    /**
     * 根据用户id获取用户租户联合信息
     *
     * @param traderId 交接人id
     * @return 返回交接人租户信息
     */
    TraderTenantVo selectTraderTenantByTraderId(String traderId);

    /**
     * 根据用户id获取用户租户联合信息列表
     *
     * @param traderIdList 交接人id列表
     * @return 返回交接人租户信息列表
     */
    List<TraderTenantVo> selectTraderTenantListByTraderIdList(@Param(value = "traderIdList") List<String> traderIdList);
}
