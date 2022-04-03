package com.purang.manifest.domain.deliver.entity;

import com.purang.manifest.domain.deliver.entity.dp.BusinessNoDP;
import com.purang.manifest.domain.deliver.entity.dp.DeliverManifestStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 出库清单领域对象 - 聚合根
 */
@Data
public class DeliverManifestDO {

    private String id;

    /**
     * 出库清单业务单号
     */
    private BusinessNoDP businessNo;

    /**
     * 清单类型（81001:询价单，81002:报价单）
     */
    private Integer manifestType;

    /**
     * 清单状态（80001:待发送, 80002:待回复, 80003:待确认, 80004:已完结, 80005:已退回）
     */
    private Integer manifestStatus;

    /**
     * 清单状态更新时间
     */
    private String statusChangeTime;

    /**
     * 票类型（12001:银票, 12002:商票, 12003:财票）
     */
    private Integer ticketType;

    /**
     * 票来源（11001:选自票库，11002:选自业务单）
     */
    private Integer ticketSource;

    /**
     * 清单票据数量
     */
    private Integer ticketNum;

    /**
     * 统一计息方式（报价单时有值，询价单时为null，90001:年息%，90002:每十万/元）
     */
    private Integer uniformInterestType;

    /**
     * 持票成本，计算规则如下<Br>
     * 票金额 * dateDiff * 6 / 36000
     */
    private BigDecimal holderCostTotal;

    /**
     * 统一报价（报价单时有值，询价单时为null）
     */
    private BigDecimal uniformQuotePrice;

    /**
     * 应收总金额（报价单时有值，询价单时为null）
     */
    private BigDecimal receiveMoneyTotal;

    /**
     * 票面总金额
     */
    private BigDecimal ticketAmountTotal;

    /**
     * 票面总利息
     */
    private BigDecimal ticketInterestTotal;

    /**
     * 支付方式（40001:先背, 40002:先打）
     */
    private Integer payType;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 出库清单创建者id
     */
    private String createUserId;

    /**
     * 出库清单更新者id
     */
    private String updateUserId;

    /**
     * 出库清单的票据列表
     */
    private List<Ticket> ticketList;

    /**
     * 发送方交易员
     */
    private Trader senderTrader;

    /**
     * 接收方交易员列表
     */
    private List<Trader> receiverTraderList;

    /**
     * 创建出库清单
     */
    public DeliverManifestDO create(List<Ticket> ticketList, Trader sendTrader,
                                    List<Trader> receiverTraders, BusinessNoDP newBusinessNo) {
        if (ticketList == null || ticketList.isEmpty()) {
            throw new IllegalArgumentException("清单票据列表不能为空！");
        }
        this.ticketList = ticketList;
        this.senderTrader = sendTrader;
        this.receiverTraderList = receiverTraders;
        this.businessNo = newBusinessNo;
        this.manifestStatus = DeliverManifestStatus.OUT_WAIT_TO_SEND;
        this.ticketNum = ticketList.size();
        return this;
    }

    /**
     * 出库成本、收益总计
     */
    public BigDecimal deliverCostAndIncomeTotal() {
        holderCostTotal = BigDecimal.ZERO;
        ticketList.stream().forEach(t -> holderCostTotal = holderCostTotal.add(t.computeHolderCost()));
        holderCostTotal = holderCostTotal.setScale(BigDecimal.ROUND_HALF_UP, 2);
        return holderCostTotal;
    }

    /**
     * 修改出库清单
     */
    public DeliverManifestDO update() {
        // 更新票列表

        // 更新对接人

        // 更新报价方式、价格
        return this;
    }

    /**
     * 发送出库清单
     *
     * @return
     */
    public Boolean send() {
        // 锁定清单（清单、票都不能编辑了）
        setManifestStatus(DeliverManifestStatus.OUT_WAIT_TO_REPLY);
        return true;
    }

    /**
     * 确认收款
     *
     * @param receiverUserId 接收人userId
     */
    public Boolean confirmReceipt(String receiverUserId) {
        // 设置出库清单为已完成状态
        setManifestStatus(DeliverManifestStatus.OUT_IS_FINISHED);

        // 更新票归属

        return true;
    }
}
