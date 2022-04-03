package com.purang.manifest.infrastructure.repo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 出库清单票关联表
 * bill_out_manifest_ticket.out_manifest_id=bill_out_stock_manifest.id
 * bill_out_manifest_ticket.ticket_id=bill_ticket_pool.id
 */
@Data
@TableName(value = "bill_out_manifest_ticket")
public class BillOutManifestTicketPO {

    /**
     * 主键id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 出库清单表id
     */
    @TableField(value = "out_manifest_id")
    private String outManifestId;

    /**
     * 票池表id
     */
    @TableField(value = "ticket_id")
    private String ticketId;

    /**
     * 该票利息
     */
    @TableField(value = "ticket_interest")
    private String ticketInterest;

    /**
     * 该票应该收款
     */
    @TableField(value = "receive_money")
    private String receiveMoney;

    /**
     * 出库清单里表列表排序号
     */
    @TableField(value = "order_no")
    private String orderNo;
}
