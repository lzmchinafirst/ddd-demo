package com.purang.manifest.domain.trader.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 交易员
 */
@Data
public class TraderDO {
    @ApiModelProperty(value = "交易员userId")
    private String userId;

    @ApiModelProperty(value = "交易员名称")
    private String userName;

    @ApiModelProperty(value = "交易员职务代码")
    private Integer postCode;

    @ApiModelProperty(value = "交易员职务名称")
    private String postName;

    @ApiModelProperty(value = "交易员手机")
    private String phone;

    @ApiModelProperty(value = "交易员租户信息")
    private Tenant traderTenant;
}
