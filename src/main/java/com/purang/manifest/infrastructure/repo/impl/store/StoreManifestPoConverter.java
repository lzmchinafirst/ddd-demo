package com.purang.manifest.infrastructure.repo.impl.store;

import com.purang.manifest.domain.store.entity.StoreManifestDO;
import com.purang.manifest.infrastructure.repo.po.BillInStockManifestPO;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

/**
 * DO、PO转换器
 */
@Mapper
public interface StoreManifestPoConverter {
    StoreManifestPoConverter INSTANCE = getMapper(StoreManifestPoConverter.class);

    BillInStockManifestPO toStoreManifestPO(StoreManifestDO storeManifestDO);

    StoreManifestDO toStoreManifestDO(BillInStockManifestPO billInStockManifestPO);
}
