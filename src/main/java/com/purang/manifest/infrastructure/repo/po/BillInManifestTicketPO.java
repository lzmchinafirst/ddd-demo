package com.purang.manifest.infrastructure.repo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 出库清单票关联表
 * bill_in_manifest_ticket.in_manifest_id=bill_in_stock_manifest.id
 * bill_in_manifest_ticket.ticket_id=bill_ticket_pool.id
 */
@Data
@TableName(value = "bill_in_manifest_ticket")
public class BillInManifestTicketPO {

    @TableId(value = "id")
    @ApiModelProperty(value = "主键id", required = true)
    private String id;

    @TableField(value = "in_manifest_id")
    @ApiModelProperty(value = "入库清单表id", required = true)
    private String inManifestId;

    @TableField(value = "ticket_id")
    @ApiModelProperty(value = "票池表id", required = true)
    private String ticketId;

    @TableField(value = "ticket_interest")
    @ApiModelProperty(value = "该票利息", required = true)
    private String ticketInterest;

    @TableField(value = "receive_money")
    @ApiModelProperty(value = "该票应该收款", required = true)
    private String receiveMoney;

    @TableField(value = "order_no")
    @ApiModelProperty(value = "出库清单里表列表排序号", required = true)
    private String orderNo;
}
