package com.purang.manifest.infrastructure.repo.impl.deliver;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.purang.manifest.adapter.dto.DeliverManifestDetailVo;
import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;
import com.purang.manifest.domain.deliver.entity.Ticket;
import com.purang.manifest.domain.deliver.entity.Trader;
import com.purang.manifest.domain.deliver.entity.dp.BusinessNoDP;
import com.purang.manifest.domain.deliver.repo.DeliverManifestRepository;
import com.purang.manifest.infrastructure.repo.dao.deliver.BillOutManifestReceiverDao;
import com.purang.manifest.infrastructure.repo.dao.deliver.BillOutManifestTicketDao;
import com.purang.manifest.infrastructure.repo.dao.deliver.BillOutStockManifestDao;
import com.purang.manifest.infrastructure.repo.dao.deliver.DeliverManifestInfoDao;
import com.purang.manifest.infrastructure.repo.po.BillOutManifestReceiverPO;
import com.purang.manifest.infrastructure.repo.po.BillOutManifestTicketPO;
import com.purang.manifest.infrastructure.repo.po.BillOutStockManifestPO;
import com.purang.manifest.infrastructure.repo.po.BillTicketPoolPO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class DeliverManifestRepositoryImpl implements DeliverManifestRepository {

    /**
     * 出库清单表（bill_out_stock_manifest）dao
     */
    private final BillOutStockManifestDao outStockManifestDao;

    private final BillOutManifestTicketDao outManifestTicketDao;

    private final BillOutManifestReceiverDao outManifestReceiverDao;

    private final DeliverManifestInfoDao deliverManifestInfoDao;

    @Autowired
    public DeliverManifestRepositoryImpl(BillOutStockManifestDao outStockManifestDao,
                                         BillOutManifestTicketDao outManifestTicketDao,
                                         BillOutManifestReceiverDao outManifestReceiverDao,
                                         DeliverManifestInfoDao deliverManifestInfoDao) {
        this.outStockManifestDao = outStockManifestDao;
        this.outManifestTicketDao = outManifestTicketDao;
        this.outManifestReceiverDao = outManifestReceiverDao;
        this.deliverManifestInfoDao = deliverManifestInfoDao;
    }

    /**
     * 领域对象（聚合根） --> DO转换成多个PO对象
     *
     * @param deliverManifestDO 出库清单领域聚合根
     */
    @Override
    public Integer saveDeliverManifest(DeliverManifestDO deliverManifestDO) {
        BillOutStockManifestPO outStockManifestPO = DeliverManifestPoConverter.INSTANCE.toDeliverManifestPO(deliverManifestDO);
        List<BillOutManifestTicketPO> outManifestTicketPos = DeliverManifestPoConverter.INSTANCE.toOutManifestTicketPoList(
                deliverManifestDO.getTicketList());
        List<BillOutManifestReceiverPO> outManifestReceiverPos = DeliverManifestPoConverter.INSTANCE.toOutManifestReceiverPoList(
                deliverManifestDO.getReceiverTraderList());

        if (StringUtils.isBlank(outStockManifestPO.getId())) {
            outStockManifestPO.setId(String.valueOf(IdWorker.getId()));
            outManifestTicketPos.stream().forEach(t -> {
                t.setId(String.valueOf(IdWorker.getId()));
                t.setOutManifestId(outStockManifestPO.getId());
                outManifestTicketDao.insert(t);
            });
            outManifestReceiverPos.stream().forEach(r -> {
                r.setId(String.valueOf(IdWorker.getId()));
                r.setOutManifestId(outStockManifestPO.getId());
                outManifestReceiverDao.insert(r);
            });
            return outStockManifestDao.insert(outStockManifestPO);
        } else {
            return outStockManifestDao.updateById(DeliverManifestPoConverter.INSTANCE.toDeliverManifestPO(deliverManifestDO));
        }
    }

    /**
     * 通过出库清单id，获取出库清单领域对象（包括出库清单接收人列表）
     *
     * @param deliverManifestId 出库清单id
     * @return 出库清单领域聚合根
     */
    public DeliverManifestDO getDeliverManifest(String deliverManifestId) {
        BillOutStockManifestPO deliverManifestPO = outStockManifestDao.selectById(deliverManifestId);
        DeliverManifestDO deliverManifestDO = DeliverManifestPoConverter.INSTANCE.toDeliverManifestDO(deliverManifestPO);
        return deliverManifestDO;
    }

    /**
     * 查取今天下一个要生成的出库清单编号
     *
     * @return 最新出库清单编号
     */
    @Override
    public BusinessNoDP findNextDeliverManifestBusinessNo() {
        // 查取今天最近的出库清单数据
        String currentDate = DateUtil.format(new Date(), DatePattern.PURE_DATE_PATTERN);
        QueryWrapper<BillOutStockManifestPO> queryWrapper = new QueryWrapper<>();
        queryWrapper.likeRight("business_no", BusinessNoDP.PREFIX + currentDate);
        queryWrapper.orderByDesc("create_time");
        queryWrapper.last("limit 1");
        BillOutStockManifestPO latestOutStockManifestPO = outStockManifestDao.selectOne(queryWrapper);

        if (latestOutStockManifestPO == null) {
            return new BusinessNoDP().createNextBusinessNo();
        } else {
            return new BusinessNoDP(latestOutStockManifestPO.getBusinessNo()).createNextBusinessNo();
        }
    }

    /**
     * 通过清单id，获取接收方列表
     *
     * @param deliverManifestId 出库清单id
     * @return 企企互联授权的对接人列表
     */
    public List<Trader> findReceiversByDeliverManifestId(String deliverManifestId) {
        QueryWrapper<BillOutManifestReceiverPO> queryWrapper = new QueryWrapper();
        queryWrapper.eq("out_manifest_id", deliverManifestId);
        List<BillOutManifestReceiverPO> outManifestReceiverPoList = outManifestReceiverDao.selectList(queryWrapper);
        return DeliverManifestPoConverter.INSTANCE.deliverReceiverPoListToTraders(outManifestReceiverPoList);
    }

    /**
     * 通过清单id，获取票据列表
     *
     * @param deliverManifestId 出库清单id
     * @return 清单票据列表
     */
    public List<Ticket> findTicketsByDeliverManifestId(String deliverManifestId) {
        List<BillTicketPoolPO> outManifestReceiverPoList = deliverManifestInfoDao.selectTicketsByDeliverManifestId(deliverManifestId);
        return DeliverManifestPoConverter.INSTANCE.toTickets(outManifestReceiverPoList);
    }

    public DeliverManifestDetailVo getDeliverManifestDetail(String deliverManifestId) {
        return deliverManifestInfoDao.selectDeliverManifestDetail(deliverManifestId);
    }
}
