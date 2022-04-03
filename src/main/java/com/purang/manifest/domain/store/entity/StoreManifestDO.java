package com.purang.manifest.domain.store.entity;

import com.purang.manifest.domain.ticket.entity.TicketDO;
import com.purang.manifest.domain.trader.entity.TraderDO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 入库清单领域对象 - 聚合根
 */
@Data
public class StoreManifestDO {

    @ApiModelProperty(value = "入库清单id", required = true)
    private String id;

    @ApiModelProperty(value = "入库清单业务单号", required = true)
    private String businessNo;

    @ApiModelProperty(value = "入库清单类型（询价单/报价单）", required = true)
    private Integer manifestType;

    @ApiModelProperty(value = "入库清单状态（待发送/待回复/待确认/已退回）", required = true)
    private Integer manifestStatus;

    @ApiModelProperty(value = "入库清单清单来源", required = true)
    private String manifestSource;

    @ApiModelProperty(value = "支付方式（40001:先背, 40002:先打）")
    private Integer payType;

    @ApiModelProperty(value = "票类型（商票/银票）", required = true)
    private Integer ticketType;

    @ApiModelProperty(value = "清单拥有的票数量", required = true)
    private Integer ticketNum;

    @ApiModelProperty(value = "回复的价格", required = true)
    private BigDecimal replyPrice;

    @ApiModelProperty(value = "计息方式（年息%/元/每十万）", required = true)
    private Integer replyInterestType;

    @ApiModelProperty(value = "票面总金额")
    private BigDecimal ticketAmountTotal;

    @ApiModelProperty(value = "合计利息", required = true)
    private BigDecimal interestTotal;

    @ApiModelProperty(value = "合计应付金额", required = true)
    private BigDecimal payMoneyTotal;

    /**
     * 发送交易员
     */
    private TraderDO senderTraderDO;

    /**
     * 接收方交易员
     */
    private TraderDO receiverTraderDO;

    /**
     * 清单票据列表
     */
    private List<TicketDO> ticketDOList;
}
