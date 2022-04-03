package com.purang.manifest.domain.deliver.entity.dp;

/**
 * 出库清单状态
 */
public class DeliverManifestStatus {
    /**
     * 待发送
     */
    public static final Integer OUT_WAIT_TO_SEND = 80001;

    /**
     * 待回复
     */
    public static final Integer OUT_WAIT_TO_REPLY = 80002;

    /**
     * 待确认
     */
    public static final Integer OUT_WAIT_TO_CONFIRM = 80003;

    /**
     * 已完结
     */
    public static final Integer OUT_IS_FINISHED = 80004;

    /**
     * 已退回
     */
    public static final Integer OUT_IS_REJECTED = 80005;
}
