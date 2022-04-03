package com.purang.manifest.infrastructure.repo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 票据出库清单表
 * bill_stock_manifest_relation.out_stock_manifest_id = bill_out_stock_manifest.id
 * bill_stock_manifest_relation.in_stock_manifest_id = bill_in_stock_manifest.id
 */
@Data
@TableName(value = "bill_stock_manifest_relation")
public class BillStockManifestRelationPO {

    @TableId(value = "id")
    @ApiModelProperty(value = "关联记录id", required = true)
    private String id;

    @TableField(value = "out_stock_manifest_id")
    @ApiModelProperty(value = "出库清单id", required = true)
    private String outStockManifestId;

    @TableField(value = "in_stock_manifest_id")
    @ApiModelProperty(value = "入库清单id", required = true)
    private String inStockManifestId;

    @TableField(value = "out_manifest_receiver_id")
    @ApiModelProperty(value = "出库清单接收方id", required = true)
    private String outManifestReceiverId;

    @TableField(value = "stock_in_flow_id")
    @ApiModelProperty(value = "票据入库流程id", required = true)
    private String stockInFlowId;

    @TableField(value = "stock_out_flow_id")
    @ApiModelProperty(value = "票据出库流程id", required = true)
    private String stockOutFlowId;

    @TableField(value = "stock_in_pay_status")
    @ApiModelProperty(value = "票据入库支付状态", required = true)
    private String stockInPayStatus;

    @TableField(value = "stock_out_pay_status")
    @ApiModelProperty(value = "票据出库支付状态", required = true)
    private String stockOutPayStatus;

    @TableField(value = "is_confirm_deal")
    @ApiModelProperty(value = "是否确认交易", required = true)
    private String isConfirmDeal;
}
