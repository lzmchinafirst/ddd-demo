package com.purang.manifest.domain.ticket.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDO {
    /**
     * 待入库
     */
    private static final int TICKET_STATUS_WAIT_IN = 9999;
    /**
     * 入库中
     */
    private static final int TICKET_STATUS_IN_STOCKING = 10000;
    /**
     * 已入库
     */
    private static final int TICKET_STATUS_ALREADY_IN = 10001;
    /**
     * 出库中
     */
    private static final int TICKET_STATUS_OUT_STOCKING = 10002;
    /**
     * 已出库
     */
    private static final int TICKET_STATUS_ALREADY_OUT = 10003;
    /**
     * 待出库
     */
    private static final int TICKET_STATUS_WAIT_OUT = 10004;

    @TableId(value = "id")
    @ApiModelProperty(value = "票id", required = true)
    private String id;

    @TableField(value = "tenant_id")
    @ApiModelProperty(value = "当前拥有该票的租户id", required = true)
    private String tenantId;

    @TableField(value = "ticket_type")
    @ApiModelProperty(value = "票据类型代码(12001:银票,12002:商票)", required = true)
    private Integer ticketType;

    @TableField(value = "ticket_type_name")
    @ApiModelProperty(value = "票据类型名称(12001:银票,12002:商票)", required = true)
    private String ticketTypeName;

    @TableField(value = "media_type")
    @ApiModelProperty(value = "票据介质代码(11001:电票,11002:纸票)", required = true)
    private Integer mediaType;

    @TableField(value = "media_type_name")
    @ApiModelProperty(value = "票据介质名称(11001:电票,11002:纸票)", required = true)
    private String mediaTypeName;

    @TableField(value = "ticket_no")
    @ApiModelProperty(value = "票号", required = true)
    private String ticketNo;

    @TableField(value = "ticket_status")
    @ApiModelProperty(value = "票状态(未使用/入库中/出库中)", required = true)
    private Integer ticketStatus;

    @TableField(value = "drawer")
    @ApiModelProperty(value = "出票人", required = true)
    private String drawer;

    @TableField(value = "drawer_account_no")
    @ApiModelProperty(value = "出票人账号", required = true)
    private String drawerAccountNo;

    @TableField(value = "drawer_open_account_bank")
    @ApiModelProperty(value = "出票人开户行", required = true)
    private String drawerOpenAccountBank;

    @TableField(value = "payee")
    @ApiModelProperty(value = "收款人", required = true)
    private String payee;

    @TableField(value = "payee_account_no")
    @ApiModelProperty(value = "收款人账号", required = true)
    private String payeeAccountNo;

    @TableField(value = "payee_open_account_bank")
    @ApiModelProperty(value = "收款人开户行", required = true)
    private String payeeOpenAccountBank;

    @TableField(value = "acceptor")
    @ApiModelProperty(value = "承兑人", required = true)
    private String acceptor;

    @TableField(value = "acceptor_account_no")
    @ApiModelProperty(value = "承兑人账号", required = true)
    private String acceptorAccountNo;

    @TableField(value = "acceptor_open_account_bank")
    @ApiModelProperty(value = "承兑人开户行", required = true)
    private String accepterOpenAccountBank;

    @TableField(value = "acceptor_open_account_bank_no")
    @ApiModelProperty(value = "承兑人开户行行号", required = true)
    private String acceptorOpenAccountBankNo;

    @TableField(value = "ticket_amount")
    @ApiModelProperty(value = "票面金额", required = true)
    private BigDecimal ticketAmount;

    @TableField(value = "issue_date")
    @ApiModelProperty(value = "出票日", required = true)
    private Integer issueDate;

    @TableField(value = "expire_date")
    @ApiModelProperty(value = "到期日", required = true)
    private String expireDate;

    @TableField(value = "can_transfer")
    @ApiModelProperty(value = "是否可转让（0:不可转让, 1:可转让）", required = true)
    private Integer canTransfer;

    @TableField(value = "latest_in_flow_id")
    @ApiModelProperty(value = "该票最近入库的入库流程id", required = true)
    private String latestInFlowId;

    @TableField(value = "latest_in_stock_time")
    @ApiModelProperty(value = "该票最近入库时间", required = true)
    private String latestInStockTime;
}
