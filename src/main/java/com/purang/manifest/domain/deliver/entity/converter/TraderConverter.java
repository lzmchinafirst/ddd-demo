package com.purang.manifest.domain.deliver.entity.converter;

import com.purang.manifest.adapter.dto.TraderTenantVo;
import com.purang.manifest.domain.deliver.entity.Trader;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface TraderConverter {
    TraderConverter INSTANCE = getMapper(TraderConverter.class);

    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "mobile", target = "phone"),
            @Mapping(source = "tenantId", target = "traderTenant.companyId"),
            @Mapping(source = "companyName", target = "traderTenant.companyName"),
            @Mapping(source = "bankAccountNo", target = "traderTenant.bankAccountNo"),
            @Mapping(source = "openAccountBank", target = "traderTenant.openAccountBank"),
            @Mapping(source = "bigLineNo", target = "traderTenant.bigLineNo")
    })
    Trader toTrader(TraderTenantVo traderTenantVo);

    List<Trader> toTraders(List<TraderTenantVo> traderTenantVo);
}
