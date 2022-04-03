package com.purang.manifest.domain.trader.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 租户
 */
@Data
public class Tenant {

    @ApiModelProperty(value = "租户企业id")
    private String companyId;

    @ApiModelProperty(value = "租户企业名称")
    private String companyName;

    @ApiModelProperty(value = "租户企业银行账号")
    private String bankAccountNo;

    @ApiModelProperty(value = "租户企业开户行")
    private String openAccountBank;

    @ApiModelProperty(value = "租户企业大额行号")
    private String bigLineNo;
}
