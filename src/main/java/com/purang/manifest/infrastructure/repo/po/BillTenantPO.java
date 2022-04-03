package com.purang.manifest.infrastructure.repo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 租户企业表
 */
@Data
@TableName(value = "bill_tenant")
public class BillTenantPO {
    /**
     * 企业id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 租户企业名称
     */
    @TableField(value = "company_name")
    private String companyName;

    /**
     * 企业银行账号
     */
    @TableField(value = "bank_account_no")
    private String bankAccountNo;

    /**
     * 企业开户行
     */
    @TableField(value = "open_account_bank")
    private Integer openAccountBank;

    /**
     * 企业开户行大额行号
     */
    @TableField(value = "big_line_no")
    private String bigLineNo;
}
