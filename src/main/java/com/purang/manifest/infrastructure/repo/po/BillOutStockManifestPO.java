package com.purang.manifest.infrastructure.repo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 票据出库清单表
 */
@Data
@TableName(value = "bill_out_stock_manifest")
public class BillOutStockManifestPO {

    /**
     * 出库清单id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 出库清单业务单号
     */
    @TableField(value = "business_no")
    private String businessNo;

    /**
     * 清单类型
     */
    @TableField(value = "manifest_type")
    private Integer manifestType;

    /**
     * 清单状态
     */
    @TableField(value = "manifest_status")
    private Integer manifestStatus;

    /**
     * 清单状态更新时间
     */
    @TableField(value = "status_change_time")
    private String statusChangeTime;

    /**
     * 票类型
     */
    @TableField(value = "ticket_type")
    private Integer ticketType;

    /**
     * 票来源（11001:选自票库，11002:选自业务单）
     */
    @TableField(value = "ticket_source")
    private Integer ticketSource;

    /**
     * 上传票据数量
     */
    @TableField(value = "ticket_num")
    private Integer ticketNum;

    /**
     * 统一计息方式（报价单时有值，询价单时为null）
     */
    @TableField(value = "uniform_interest_type")
    private Integer uniformInterestType;

    /**
     * 统一报价（报价单时有值，询价单时为null）
     */
    @TableField(value = "uniform_quote_price")
    private BigDecimal uniformQuotePrice;

    /**
     * 持票成本，计算规则如下<Br>
     * 票金额 * dateDiff * 6 / 36000
     */
    private BigDecimal holderCostTotal;

    /**
     * 应收总金额（报价单时有值，询价单时为null）
     */
    @TableField(value = "receive_money_total")
    private BigDecimal receiveMoneyTotal;

    /**
     * 票面总金额
     */
    @TableField(value = "ticket_amount_total")
    private BigDecimal ticketAmountTotal;

    /**
     * 票面总利息
     */
    @TableField(value = "ticket_interest_total")
    private BigDecimal ticketInterestTotal;

    /**
     * 支付方式（40001:先背, 40002:先打）
     */
    @TableField(value = "pay_type")
    private Integer payType;

    /**
     * 发送方userId
     */
    @TableField(value = "sender_user_id")
    private String senderUserId;

    /**
     * 发送方名称
     */
    @TableField(value = "sender_user_name")
    private String senderUserName;

    /**
     * 发送方企业名称
     */
    @TableField(value = "sender_company_name")
    private String senderCompanyName;

    /**
     * 发送方账号
     */
    @TableField(value = "sender_account_no")
    private String senderAccountNo;

    /**
     * 发送方开户行
     */
    @TableField(value = "sender_open_account_bank")
    private String senderOpenAccountBank;

    /**
     * 发送方大额行号
     */
    @TableField(value = "sender_big_line_no")
    private String senderBigLineNo;

    /**
     * 发送方职务代码
     */
    @TableField(value = "sender_post_code")
    private Integer senderPostCode;

    /**
     * 发送方职务名称
     */
    @TableField(value = "sender_post_name")
    private String senderPostName;

    /**
     * 发送方手机
     */
    @TableField(value = "sender_phone")
    private String senderPhone;

    /**
     * 备注
     */
    @TableField(value = "remarks")
    private String remarks;

    /**
     * 出库清单创建者id
     */
    @TableField(value = "create_user_id")
    private String createUserId;

    /**
     * 出库清单创建时间
     */
    @TableField(value = "create_time")
    private String createTime;

    /**
     * 出库清单更新者id
     */
    @TableField(value = "update_user_id")
    private String updateUserId;
}
