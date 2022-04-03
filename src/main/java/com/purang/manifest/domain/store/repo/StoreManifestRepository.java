package com.purang.manifest.domain.store.repo;

import com.purang.manifest.domain.store.entity.StoreManifestDO;

public interface StoreManifestRepository {

    /**
     * 保存出库清单
     *
     * @return
     */
    StoreManifestDO saveStoreManifest(StoreManifestDO storeManifestDO);

    /**
     * 通过出库清单id，获取出库清单领域对象
     *
     * @param storeManifestId
     * @return
     */
    StoreManifestDO getStoreManifest(String storeManifestId);
}
