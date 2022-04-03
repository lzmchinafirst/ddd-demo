package com.purang.manifest.adapter.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 出库清单Form表单更新命令
 */
@Data
public class DeliverManifestUpdateCmd {

    @ApiModelProperty(value = "出库清单id")
    private String id;

    @ApiModelProperty(value = "统一计息方式（询价单时有值，报价单时为null，年息%:13001，每十万/元:13002）")
    private Integer uniformInterestType;

    @ApiModelProperty(value = "统一报价（报价单时有值，询价单时为null）")
    private BigDecimal uniformQuotePrice;

    @ApiModelProperty(value = "应收总金额（报价单时有值，询价单时为null）")
    private BigDecimal receiveMoneyTotal;

    @ApiModelProperty(value = "支付方式（40001:先背，40002:先打）")
    private Integer payType;

    @ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 发送方交易员userId
     */
    private String senderUserId;

    /**
     * 接收方交易员userId列表
     */
    private List<String> receiverUserIdList;

    /**
     * 出库清单的票据列表
     */
    private List<String> ticketIdList;
}
