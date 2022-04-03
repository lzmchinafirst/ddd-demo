package com.purang.manifest.domain.deliver.entity;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Ticket {

    /**
     * 票id
     */
    private String id;

    /**
     * 当前拥有该票的租户id
     */
    private String tenantId;

    /**
     * 票据类型代码(12001:银票,12002:商票)
     */
    private Integer ticketType;

    /**
     * 票据类型名称(12001:银票,12002:商票)
     */
    private String ticketTypeName;

    /**
     * 票据介质代码(11001:电票,11002:纸票)
     */
    private Integer mediaType;

    /**
     * 票据介质名称(11001:电票,11002:纸票)
     */
    private String mediaTypeName;

    /**
     * 票号
     */
    private String ticketNo;

    /**
     * 票状态(未使用/入库中/出库中)
     */
    private Integer ticketStatus;

    /**
     * 出票人
     */
    private String drawer;

    /**
     * 出票人账号
     */
    private String drawerAccountNo;

    /**
     * 出票人开户行
     */
    private String drawerOpenAccountBank;

    /**
     * 收款人
     */
    private String payee;

    /**
     * 收款人账号
     */
    private String payeeAccountNo;

    /**
     * 收款人开户行
     */
    private String payeeOpenAccountBank;

    /**
     * 承兑人
     */
    private String acceptor;

    /**
     * 承兑人账号
     */
    private String acceptorAccountNo;

    /**
     * 承兑人开户行
     */
    private String accepterOpenAccountBank;

    /**
     * 承兑人开户行行号
     */
    private String acceptorOpenAccountBankNo;

    /**
     * 票面金额
     */
    private BigDecimal ticketAmount;

    /**
     * 出票日
     */
    private Integer issueDate;

    /**
     * 到期日
     */
    private String expireDate;

    /**
     * 是否可转让（0:不可转让, 1:可转让）
     */
    private Integer canTransfer;

    /**
     * 该票最近入库的入库流程id
     */
    private String latestInFlowId;

    /**
     * 该票最近入库时间
     */
    private String latestInStockTime;

    /**
     * 该票应该收款<br>
     * 票据金额 + 持票成本
     */
    private String receiveMoney;

    /**
     * 持票成本，计算规则如下<Br>
     * 票金额 * dateDiff * 6 / 36000
     *
     * @return
     */
    public BigDecimal computeHolderCost() {
        long diffDay = DateUtil.betweenDay(DateUtil.parse(latestInStockTime, DatePattern.PURE_DATETIME_PATTERN),
                DateUtil.date(), true);

        System.out.println(this.ticketAmount.toPlainString() + " * " + String.valueOf(diffDay) + " * 6 / 36000");
        BigDecimal result = this.ticketAmount
                .multiply(new BigDecimal(diffDay))
                .multiply(new BigDecimal(6))
                .divide(new BigDecimal(36000), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println(result.stripTrailingZeros().toPlainString());
        return result;
    }
}
