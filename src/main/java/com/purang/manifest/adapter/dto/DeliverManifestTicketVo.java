package com.purang.manifest.adapter.dto;

import lombok.Data;

/**
 * 出库清单票据列表视图
 */
@Data
public class DeliverManifestTicketVo {
    private String tenantId;
    private String ticketType;
    private String ticketTypeName;
    private String mediaType;
    private String mediaTypeName;
    private String ticketNo;
    private String ticketStatus;
    private String drawer;
    private String drawerAccountNo;
    private String drawerOpenAccountBank;
    private String payee;
    private String payeeAccountNo;
    private String payeeOpenAccountBank;
    private String acceptor;
    private String acceptorAccountNo;
    private String acceptorOpenAccountBank;
    private String acceptorOpenAccountBankNo;
    private String issueDate;
    private String expireDate;
    private String canTransfer;
}
