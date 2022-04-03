package com.purang.manifest.adapter.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 出库清单管理列表，查询过滤条件封装
 */
@Data
public class DeliverManifestQry {

    @ApiModelProperty(value = "出库清单业务单号")
    private String businessNo;
}
