package com.purang.manifest.infrastructure.repo.impl.store;

import com.purang.manifest.domain.deliver.entity.Ticket;
import com.purang.manifest.domain.store.entity.StoreManifestDO;
import com.purang.manifest.domain.store.repo.StoreManifestRepository;
import com.purang.manifest.infrastructure.repo.dao.store.BillInManifestTicketDao;
import com.purang.manifest.infrastructure.repo.dao.store.BillInStockManifestDao;
import com.purang.manifest.infrastructure.repo.po.BillInStockManifestPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StoreManifestRepositoryImpl implements StoreManifestRepository {

    /**
     * 入库清单表（bill_in_stock_manifest）dao
     */
    private final BillInStockManifestDao inStockManifestDao;

    private final BillInManifestTicketDao inManifestTicketDao;

    @Autowired
    public StoreManifestRepositoryImpl(BillInStockManifestDao inStockManifestDao,
                                       BillInManifestTicketDao inManifestTicketDao) {
        this.inStockManifestDao = inStockManifestDao;
        this.inManifestTicketDao = inManifestTicketDao;
    }

    /**
     * 领域对象（聚合根） --> DO转换成多个PO对象
     *
     * @param storeManifestDO 出库清单领域聚合根
     */
    @Override
    public StoreManifestDO saveStoreManifest(StoreManifestDO storeManifestDO) {
        return null;
    }

    /**
     * 通过入库清单id，获取入库清单领域对象（包括入库清单票据列表）
     *
     * @param storeManifestId
     * @return
     */
    public StoreManifestDO getStoreManifest(String storeManifestId) {
        BillInStockManifestPO storeManifestPO = inStockManifestDao.selectById(storeManifestId);
        StoreManifestDO storeManifestDO = StoreManifestPoConverter.INSTANCE.toStoreManifestDO(storeManifestPO);
        return storeManifestDO;
    }

    /**
     * 通过入库清单id，获取票据列表
     *
     * @param storeManifestId
     * @return
     */
    public List<Ticket> findTicketsByStoreManifestId(String storeManifestId) {
        return null;
    }
}
