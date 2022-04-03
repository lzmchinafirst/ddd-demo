package com.purang.manifest.infrastructure.repo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 用户表
 */
@Data
@TableName(value = "bill_trader")
public class BillTraderPO {
    /**
     * 交易员userId
     */
    @TableId(value = "id")
    private String id;

    /**
     * 交易员所属租户企业
     */
    @TableField(value = "company_id")
    private String companyId;

    /**
     * 交易员名称
     */
    @TableField(value = "user_name")
    private String userName;

    /**
     * 交易员职务代码
     */
    @TableField(value = "post_code")
    private Integer postCode;

    /**
     * 交易员职务名称
     */
    @TableField(value = "post_name")
    private String postName;

    /**
     * 交易员手机
     */
    @TableField(value = "mobile")
    private String mobile;
}
