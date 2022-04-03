package com.purang.manifest.adapter.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 入库清单Form表单提交命令
 */
@Data
public class StoreManifestFormCmd {

    @ApiModelProperty(value = "入库清单id")
    private String id;

    @ApiModelProperty(value = "入库清单业务单号")
    private String businessNo;

    @ApiModelProperty(value = "清单类型（81001:询价单，81002：报价单）", required = true)
    private Integer manifestType;

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
}
