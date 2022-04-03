package com.purang.manifest.adapter.dto;

import lombok.Data;

/**
 * 交接人租户信息视图
 */
@Data
public class TraderTenantVo {

    /**
     * 交易员userId
     */
    private String id;

    /**
     * 交易员名称
     */
    private String userName;

    /**
     * 交易员职务代码
     */
    private Integer postCode;

    /**
     * 交易员职务名称
     */
    private String postName;

    /**
     * 交易员手机
     */
    private String mobile;

    /**
     * 交易员所属租户企业id
     */
    private String tenantId;

    /**
     * 租户企业名称
     */
    private String companyName;

    /**
     * 企业银行账号
     */
    private String bankAccountNo;

    /**
     * 企业开户行
     */
    private String openAccountBank;

    /**
     * 企业开户行大额行号
     */
    private String bigLineNo;
}
