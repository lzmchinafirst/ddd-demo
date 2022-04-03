package com.purang.manifest.infrastructure.types;

/**
 * 出库清单状态
 */
public class DeliverManifestStatus {
    public static final int OUT_WAIT_TO_SEND = 80001; //待发送
    public static final int OUT_WAIT_TO_REPLY = 80002; //待回复
    public static final int OUT_WAIT_TO_CONFIRM = 80003; //待确认
    public static final int OUT_IS_FINISHED = 80004; //已完结
    public static final int OUT_IS_REJECTED = 80005; //已退回
}
