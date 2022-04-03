package com.purang.manifest.infrastructure.repo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 企业的票据池
 */
@Data
@TableName(value = "bill_ticket_pool")
public class BillTicketPoolPO {

    /**
     * 票id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 当前拥有该票的租户id
     */
    @TableField(value = "tenant_id")
    private String tenantId;

    /**
     * 票据类型代码(12001:银票,12002:商票)
     */
    @TableField(value = "ticket_type")
    private Integer ticketType;

    /**
     * 票据类型名称(12001:银票,12002:商票)
     */
    @TableField(value = "ticket_type_name")
    private String ticketTypeName;

    /**
     * 票据介质代码(11001:电票,11002:纸票)
     */
    @TableField(value = "media_type")
    private Integer mediaType;

    /**
     * 票据介质名称(11001:电票,11002:纸票)
     */
    @TableField(value = "media_type_name")
    private String mediaTypeName;

    /**
     * 票号
     */
    @TableField(value = "ticket_no")
    private String ticketNo;

    /**
     * 票状态(待入库9999,入库中10000,已入库10001,出库中10002,已出库10003,待出库10004)
     */
    @TableField(value = "ticket_status")
    private Integer ticketStatus;

    /**
     * 出票人
     */
    @TableField(value = "drawer")
    private String drawer;

    /**
     * 出票人账号
     */
    @TableField(value = "drawer_account_no")
    private String drawerAccountNo;

    /**
     * 出票人开户行
     */
    @TableField(value = "drawer_open_account_bank")
    private String drawerOpenAccountBank;

    /**
     * 收款人
     */
    @TableField(value = "payee")
    private String payee;

    /**
     * 收款人账号
     */
    @TableField(value = "payee_account_no")
    private String payeeAccountNo;

    /**
     * 收款人开户行
     */
    @TableField(value = "payee_open_account_bank")
    private String payeeOpenAccountBank;

    /**
     * 承兑人
     */
    @TableField(value = "acceptor")
    private String acceptor;

    /**
     * 承兑人账号
     */
    @TableField(value = "acceptor_account_no")
    private String acceptorAccountNo;

    /**
     * 承兑人开户行
     */
    @TableField(value = "acceptor_open_account_bank")
    private String accepterOpenAccountBank;

    /**
     * 承兑人开户行行号
     */
    @TableField(value = "acceptor_open_account_bank_no")
    private String acceptorOpenAccountBankNo;

    /**
     * 票面金额
     */
    @TableField(value = "ticket_amount")
    private BigDecimal ticketAmount;

    /**
     * 出票日
     */
    @TableField(value = "issue_date")
    private Integer issueDate;

    /**
     * 到期日
     */
    @TableField(value = "expire_date")
    private String expireDate;

    /**
     * 是否可转让（0:不可转让, 1:可转让）
     */
    @TableField(value = "can_transfer")
    private Integer canTransfer;

    /**
     * 该票最近入库的入库流程id
     */
    @TableField(value = "latest_in_flow_id")
    private String latestInFlowId;

    /**
     * 该票最近入库时间
     */
    @TableField(value = "latest_in_stock_time")
    private String latestInStockTime;
/*
    private Integer plusDay;        // 加天
    private Integer dayNum;        // 剩余天数

    */
}
