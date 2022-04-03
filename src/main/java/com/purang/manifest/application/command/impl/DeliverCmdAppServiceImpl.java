package com.purang.manifest.application.command.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.purang.manifest.adapter.dto.TraderTenantVo;
import com.purang.manifest.application.command.DeliverCmdAppService;
import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;
import com.purang.manifest.domain.deliver.entity.Ticket;
import com.purang.manifest.domain.deliver.entity.Trader;
import com.purang.manifest.domain.deliver.entity.converter.TicketConverter;
import com.purang.manifest.domain.deliver.entity.converter.TraderConverter;
import com.purang.manifest.domain.deliver.entity.dp.BusinessNoDP;
import com.purang.manifest.domain.deliver.repo.DeliverManifestRepository;
import com.purang.manifest.domain.deliver.service.DeliverManifestDomainService;
import com.purang.manifest.domain.ticket.entity.TicketDO;
import com.purang.manifest.domain.ticket.repo.TicketPoolRepository;
import com.purang.manifest.domain.trader.entity.TraderDO;
import com.purang.manifest.domain.trader.repo.TraderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 命令应用层负责领域的编排、事务的处理，领域对象的存储
 */
@Slf4j
@Component
public class DeliverCmdAppServiceImpl implements DeliverCmdAppService {

    private final DeliverManifestRepository deliverManifestRepository;

    private final TicketPoolRepository ticketPoolRepository;

    private final TraderRepository traderRepository;

    private final DeliverManifestDomainService deliverManifestDomainService;

    @Autowired
    public DeliverCmdAppServiceImpl(DeliverManifestRepository deliverManifestRepository,
                                    TicketPoolRepository ticketPoolRepository,
                                    TraderRepository traderRepository,
                                    DeliverManifestDomainService deliverManifestDomainService) {
        this.deliverManifestRepository = deliverManifestRepository;
        this.ticketPoolRepository = ticketPoolRepository;
        this.traderRepository = traderRepository;
        this.deliverManifestDomainService = deliverManifestDomainService;
    }

    /**
     * 创建出库清单
     *
     * @param deliverManifestDO  出库清单领域聚合根
     * @param senderUserId       出库清单发送方对接人userId
     * @param ticketIdList       出库清单票据列表
     * @param receiverUserIdList 出库清单接收方对接人userId列表
     * @return 返回保存后的出库清单领域信息
     */
    @Override
    @DS("master")
    @Transactional
    public DeliverManifestDO createDeliverManifest(DeliverManifestDO deliverManifestDO, String senderUserId,
                                                   List<String> ticketIdList, List<String> receiverUserIdList) {
        // 装配丰富领域对象内容
        List<TicketDO> ticketDos = ticketPoolRepository.findList(ticketIdList);
        TraderTenantVo traderTenantVo = traderRepository.getTraderTenant(senderUserId);
        List<TraderTenantVo> traderTenantVos = traderRepository.findTraderTenantList(receiverUserIdList);
        BusinessNoDP newBusinessNo = deliverManifestRepository.findNextDeliverManifestBusinessNo();
        List<Ticket> tickets = TicketConverter.INSTANCE.toTickets(ticketDos);
        Trader sendTrader = TraderConverter.INSTANCE.toTrader(traderTenantVo);
        List<Trader> receiverTraders = TraderConverter.INSTANCE.toTraders(traderTenantVos);

        // 根据票据对象列表、发送方对接人、接收方对接人列表、业务单号，生成出库清单领域聚合根
        deliverManifestDO.create(tickets, sendTrader, receiverTraders, newBusinessNo);
        // 出库清单领域实体 计算成本收益
        deliverManifestDO.deliverCostAndIncomeTotal();

        // 调用出库清单仓储，保存出库清单领域数据
        deliverManifestRepository.saveDeliverManifest(deliverManifestDO);

        // 消息推送通知，提示用户确认是否进行入库清单
        return deliverManifestDO;
    }

    /**
     * 修改出库清单
     *
     * @param deliverManifestDO  出库清单领域聚合根
     * @param senderUserId       出库清单发送方对接人userId
     * @param ticketIdList       出库清单票据列表
     * @param receiverUserIdList 出库清单接收方对接人userId列表
     * @return 返回保存后的出库清单领域信息
     */
    @Override
    public DeliverManifestDO updateDeliverManifest(DeliverManifestDO deliverManifestDO, String senderUserId,
                                                   List<String> ticketIdList, List<String> receiverUserIdList) {
        return deliverManifestDO;
    }

    /**
     * 清单合并
     * 将第2个清单合并到第1个清单，并设置对接人
     *
     * @param deliverManifestId1 第1个清单id
     * @param deliverManifestId2 第2个清单id
     * @param senderUserId       对接人userId
     * @return 合并结果
     */
    @Override
    public Boolean merge(String deliverManifestId1, String deliverManifestId2, String senderUserId) {
        DeliverManifestDO deliverManifestDo1 = deliverManifestRepository.getDeliverManifest(deliverManifestId1);
        DeliverManifestDO deliverManifestDo2 = deliverManifestRepository.getDeliverManifest(deliverManifestId2);
        TraderDO mainTrader = traderRepository.get(senderUserId);

        // 多个领域对象间的业务计算、逻辑，放在某个领域对象不合适，就放在领域服务中处理
        deliverManifestDomainService.merge(deliverManifestDo1, deliverManifestDo2, mainTrader);

        deliverManifestRepository.saveDeliverManifest(deliverManifestDo1);
        deliverManifestRepository.saveDeliverManifest(deliverManifestDo2);
        return true;
    }

    /**
     * 发送出库清单
     *
     * @param deliverManifestId 将发送的出库清单的id
     */
    @Override
    public Boolean sendDeliverManifest(String deliverManifestId) {
        DeliverManifestDO deliverManifest = deliverManifestRepository.getDeliverManifest(deliverManifestId);
        List<Trader> receiverTraderList = deliverManifestRepository.findReceiversByDeliverManifestId(deliverManifestId);
        deliverManifest.setReceiverTraderList(receiverTraderList);

        deliverManifest.send();

        // 消息推送通知，提示用户确认是否进行入库清单
        return null;
    }

    /**
     * 确认收款
     *
     * @param deliverManifestId 出库清单id
     * @param receiverUserId    确认收款接收方userId
     * @return 返回确认收款结果
     */
    @Override
    @Transactional
    public Boolean confirmReceipt(String deliverManifestId, String receiverUserId) {
        // 准备当前的领域数据
        DeliverManifestDO deliverManifest = deliverManifestRepository.getDeliverManifest(deliverManifestId);
        List<Trader> receiverTraderList = deliverManifestRepository.findReceiversByDeliverManifestId(deliverManifestId);
        List<Ticket> ticketList = deliverManifestRepository.findTicketsByDeliverManifestId(deliverManifestId);
        deliverManifest.setTicketList(ticketList);
        deliverManifest.setReceiverTraderList(receiverTraderList);

        // 出库清单领域服务，执行确认收款
        deliverManifest.confirmReceipt(receiverUserId);
        // 保存新出库清单领域服务
        deliverManifestRepository.saveDeliverManifest(deliverManifest);

        // 批量调整出库清单票状态
        ticketPoolRepository.batchModifyTicketStatus(TicketConverter.INSTANCE.toTicketDos(ticketList));

        // 保存入库清单领域
        return null;
    }
}
