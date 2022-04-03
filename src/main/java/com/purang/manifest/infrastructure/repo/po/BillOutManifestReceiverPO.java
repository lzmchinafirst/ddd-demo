package com.purang.manifest.infrastructure.repo.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 出库清单接收人信息表
 * bill_out_manifest_receiver.out_manifest_id=bill_out_stock_manifest.id
 */
@Data
@TableName(value = "bill_out_manifest_receiver")
public class BillOutManifestReceiverPO {

    /**
     * 出库清单接收人关联表id
     */
    @TableId(value = "id")
    private String id;

    /**
     * 出库清单表id
     */
    @TableField(value = "out_manifest_id")
    private String outManifestId;

    /**
     * 出库清单接收人租户id
     */
    @TableField(value = "receiver_tenant_id")
    private String receiverTenantId;

    /**
     * 出库清单接收人租户名称
     */
    @TableField(value = "receiver_tenant_name")
    private String receiverTenantName;

    /**
     * 出库清单接收人userId
     */
    @TableField(value = "receiver_user_id")
    private String receiverUserId;

    /**
     * 出库清单接收人userName
     */
    @TableField(value = "receiver_user_name")
    private String receiverUserName;

    /**
     * 出库清单接收人职务代码
     */
    @TableField(value = "receiver_post_code")
    private String receiverPostCode;

    /**
     * 出库清单接收人职务名称
     */
    @TableField(value = "receiver_post_name")
    private String receiverPostName;

    /**
     * 出库清单接收人手机号
     */
    @TableField(value = "receiver_mobile")
    private String receiverMobile;
}
