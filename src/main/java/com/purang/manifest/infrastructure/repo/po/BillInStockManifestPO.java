package com.purang.manifest.infrastructure.repo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 票据入库清单表
 */
@Data
@TableName(value = "bill_in_stock_manifest")
public class BillInStockManifestPO {

    @TableId(value = "id")
    @ApiModelProperty(value = "入库清单id", required = true)
    private String id;

    @TableField(value = "business_no")
    @ApiModelProperty(value = "入库清单业务单号", required = true)
    private String businessNo;

    @TableField(value = "manifest_type")
    @ApiModelProperty(value = "入库清单类型（询价单/报价单）", required = true)
    private Integer manifestType;

    @TableField(value = "manifest_status")
    @ApiModelProperty(value = "入库清单状态（待发送/待回复/待确认/已退回）", required = true)
    private Integer manifestStatus;

    @TableField(value = "manifest_source")
    @ApiModelProperty(value = "入库清单清单来源", required = true)
    private String manifestSource;

    @TableField(value = "pay_type")
    @ApiModelProperty(value = "支付方式（40001:先背, 40002:先打）")
    private Integer payType;

    @TableField(value = "ticket_type")
    @ApiModelProperty(value = "票类型（商票/银票）", required = true)
    private Integer ticketType;

    @TableField(value = "ticket_num")
    @ApiModelProperty(value = "清单拥有的票数量", required = true)
    private Integer ticketNum;

    @TableField(value = "reply_price")
    @ApiModelProperty(value = "回复的价格", required = true)
    private BigDecimal replyPrice;

    @TableField(value = "reply_interest_type")
    @ApiModelProperty(value = "计息方式（年息%/元/每十万）", required = true)
    private Integer replyInterestType;

    @TableField(value = "ticket_amount_total")
    @ApiModelProperty(value = "票面总金额")
    private BigDecimal ticketAmountTotal;

    @TableField(value = "interest_total")
    @ApiModelProperty(value = "合计利息", required = true)
    private BigDecimal interestTotal;

    @TableField(value = "pay_money_total")
    @ApiModelProperty(value = "合计应付金额", required = true)
    private BigDecimal payMoneyTotal;

    @TableField(value = "sender_user_id")
    @ApiModelProperty(value = "发送方userId")
    private String senderUserId;

    @TableField(value = "sender_user_name")
    @ApiModelProperty(value = "发送方名称")
    private String senderUserName;

    @TableField(value = "sender_company_name")
    @ApiModelProperty(value = "发送方企业名称")
    private String senderCompanyName;

    @TableField(value = "sender_account")
    @ApiModelProperty(value = "发送方账号")
    private String senderAccount;

    @TableField(value = "sender_open_account_bank")
    @ApiModelProperty(value = "发送方开户行")
    private String senderOpenAccountBank;

    @TableField(value = "sender_big_line_no")
    @ApiModelProperty(value = "发送方大额行号")
    private String senderBigLineNo;

    @TableField(value = "sender_post_code")
    @ApiModelProperty(value = "发送方职务代码")
    private Integer senderPostCode;

    @TableField(value = "sender_post_name")
    @ApiModelProperty(value = "发送方职务名称")
    private String senderPostName;

    @TableField(value = "sender_phone")
    @ApiModelProperty(value = "发送方手机")
    private String senderPhone;

    @TableField(value = "receiver_user_id")
    @ApiModelProperty(value = "转入方对接人id", required = true)
    private String receiverUserId;

    @TableField(value = "receiver_user_name")
    @ApiModelProperty(value = "转入方对接人名称", required = true)
    private String receiverUserName;

    @TableField(value = "receiver_company_name")
    @ApiModelProperty(value = "转入方企业名称", required = true)
    private String receiverCompanyName;

    @TableField(value = "receiver_account_no")
    @ApiModelProperty(value = "转入方账号", required = true)
    private String receiverAccountNo;

    @TableField(value = "receiver_open_account_bank")
    @ApiModelProperty(value = "转入方开户行", required = true)
    private String receiverOpenAccountBank;

    @TableField(value = "receiver_big_line_no")
    @ApiModelProperty(value = "转入方大额行号", required = true)
    private String receiverBigLineNo;

    @TableField(value = "receiver_post_code")
    @ApiModelProperty(value = "转入方职务代码", required = true)
    private Integer receiverPostCode;

    @TableField(value = "receiver_post_name")
    @ApiModelProperty(value = "转入方职务名称", required = true)
    private String receiverPostName;

    @TableField(value = "receiver_phone")
    @ApiModelProperty(value = "转入方联系电话", required = true)
    private String receiverPhone;

    @TableField(value = "create_user_id")
    @ApiModelProperty(value = "入库清单创建者id")
    private String createUserId;

    @TableField(value = "update_user_id")
    @ApiModelProperty(value = "入库清单更新者id")
    private String updateUserId;
}
