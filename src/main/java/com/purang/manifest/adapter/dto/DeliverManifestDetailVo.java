package com.purang.manifest.adapter.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 出库清单详情视图
 */
@Data
public class DeliverManifestDetailVo {

    @ApiModelProperty(value = "出库清单id")
    private String id;

    @ApiModelProperty(value = "出库清单业务单号")
    private String businessNo;

    @ApiModelProperty(value = "清单类型（81001:询价单，81002：报价单）", required = true)
    private Integer manifestType;

    @ApiModelProperty(value = "票类型（12001:银票, 12002:商票, 12003:财票）", required = true)
    private Integer ticketType;

    @ApiModelProperty(value = "票来源（11001:选自票库，11002:选自业务单）", required = true)
    private Integer ticketSource;

    @ApiModelProperty(value = "票据数量", required = true)
    private Integer ticketNum;

    @ApiModelProperty(value = "统一计息方式（询价单时有值，报价单时为null，年息%:13001，每十万/元:13002）")
    private Integer uniformInterestType;

    @ApiModelProperty(value = "持有总成本")
    private BigDecimal holderCostTotal;

    @ApiModelProperty(value = "统一报价（报价单时有值，询价单时为null）")
    private BigDecimal uniformQuotePrice;

    @ApiModelProperty(value = "应收总金额（报价单时有值，询价单时为null）")
    private BigDecimal receiveMoneyTotal;

    @ApiModelProperty(value = "支付方式（40001:先背，40002:先打）")
    private Integer payType;

    @ApiModelProperty(value = "备注")
    private String remarks;

    @ApiModelProperty(value = "发送方对接人姓名")
    private String senderUserName;

    @ApiModelProperty(value = "接收方对接人姓名")
    private String receiverUserName;
}
