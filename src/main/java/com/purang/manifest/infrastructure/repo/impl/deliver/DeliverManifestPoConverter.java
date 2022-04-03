package com.purang.manifest.infrastructure.repo.impl.deliver;

import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;
import com.purang.manifest.domain.deliver.entity.Ticket;
import com.purang.manifest.domain.deliver.entity.Trader;
import com.purang.manifest.infrastructure.repo.po.BillOutManifestReceiverPO;
import com.purang.manifest.infrastructure.repo.po.BillOutManifestTicketPO;
import com.purang.manifest.infrastructure.repo.po.BillOutStockManifestPO;
import com.purang.manifest.infrastructure.repo.po.BillTicketPoolPO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

/**
 * DO、PO转换器
 */
@Mapper
public interface DeliverManifestPoConverter {
    DeliverManifestPoConverter INSTANCE = getMapper(DeliverManifestPoConverter.class);

    @Mappings({
            @Mapping(source = "businessNo.value", target = "businessNo"),
            @Mapping(source = "senderTrader.userId", target = "senderUserId"),
            @Mapping(source = "senderTrader.userName", target = "senderUserName"),
            @Mapping(source = "senderTrader.traderTenant.companyName", target = "senderCompanyName")
    })
    BillOutStockManifestPO toDeliverManifestPO(DeliverManifestDO deliverManifestDO);

    @Mapping(source = "id", target = "ticketId")
    BillOutManifestTicketPO toOutManifestTicketPO(Ticket ticket);

    List<BillOutManifestTicketPO> toOutManifestTicketPoList(List<Ticket> tickets);

    @Mappings({
            @Mapping(source = "traderTenant.companyId", target = "receiverTenantId"),
            @Mapping(source = "traderTenant.companyName", target = "receiverTenantName"),
            @Mapping(source = "userId", target = "receiverUserId"),
            @Mapping(source = "userName", target = "receiverUserName"),
            @Mapping(source = "postCode", target = "receiverPostCode"),
            @Mapping(source = "postName", target = "receiverPostName"),
            @Mapping(source = "phone", target = "receiverMobile")
    })
    BillOutManifestReceiverPO toOutManifestReceiverPO(Trader trader);

    List<BillOutManifestReceiverPO> toOutManifestReceiverPoList(List<Trader> traders);

    /**
     * 票池DB数据  -->  领域对象
     *
     * @param billOutStockManifestPO
     * @return
     */
    @Mapping(source = "businessNo", target = "businessNo.value")
    DeliverManifestDO toDeliverManifestDO(BillOutStockManifestPO billOutStockManifestPO);

    /**
     * 票池DB数据  -->  领域对象
     *
     * @param billTicketPoolPO
     * @return
     */
    Ticket toTicket(BillTicketPoolPO billTicketPoolPO);

    /**
     * 票池DB数据列表  -->  领域对象列表
     *
     * @param billTicketPoolPoList
     * @return
     */
    List<Ticket> toTickets(List<BillTicketPoolPO> billTicketPoolPoList);

    /**
     * 对接人DB数据  -->  对接人对象
     *
     * @param traderPO
     * @return
     */
//    Trader toTrader(BillTraderPO traderPO);

    /**
     * 对接人DB数据列表  -->  对接人对象列表
     *
     * @param
     * @return
     */
//    List<Trader> toTraders(List<BillTraderPO> billTraderPoList);

    Trader deliverReceiverPoToTrader(BillOutManifestReceiverPO outManifestReceiverPO);

    List<Trader> deliverReceiverPoListToTraders(List<BillOutManifestReceiverPO> outManifestReceiverPO);
}
