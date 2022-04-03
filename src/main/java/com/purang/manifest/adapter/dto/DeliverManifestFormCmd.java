package com.purang.manifest.adapter.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

/**
 * 出库清单Form表单创建命令
 */
@Data
public class DeliverManifestFormCmd {

    @ApiModelProperty(value = "清单类型（81001:询价单，81002：报价单）", required = true)
    private Integer manifestType;

    @ApiModelProperty(value = "票类型（12001:银票, 12002:商票, 12003:财票）", required = true)
    private Integer ticketType;

    @ApiModelProperty(value = "票来源（11001:选自票库，11002:选自业务单）", required = true)
    private Integer ticketSource;

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
    @NotEmpty
    @Size(min = 1, max = 20, message = "请您上传1~20张票据！")
    private List<String> ticketIdList;
}
