package com.purang.manifest.domain.deliver.entity.dp;

/**
 * 票据状态
 */
public class TicketStatus {
    /**
     * 待入库
     */
    public static final int TICKET_STATUS_WAIT_IN = 9999;
    /**
     * 入库中
     */
    public static final int TICKET_STATUS_IN_STOCKING = 10000;
    /**
     * 已入库
     */
    public static final int TICKET_STATUS_ALREADY_IN = 10001;
    /**
     * 出库中
     */
    public static final int TICKET_STATUS_OUT_STOCKING = 10002;
    /**
     * 已出库
     */
    public static final int TICKET_STATUS_ALREADY_OUT = 10003;
    /**
     * 待出库
     */
    public static final int TICKET_STATUS_WAIT_OUT = 10004;
}
