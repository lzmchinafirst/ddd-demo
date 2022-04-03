package com.purang.manifest.adapter.web;

import com.purang.manifest.adapter.converter.DeliverManifestDoConverter;
import com.purang.manifest.adapter.dto.*;
import com.purang.manifest.application.command.DeliverCmdAppService;
import com.purang.manifest.application.query.DeliverQryAppService;
import com.purang.manifest.domain.deliver.entity.DeliverManifestDO;
import com.purang.manifest.domain.deliver.entity.Ticket;
import com.purang.response.CommonResponseCode;
import com.purang.response.api.ResponseEntity;
import com.purang.response.api.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 适配器层入口，主要负责请求参数原始校验、参数的装配，然后将生成的领域对象DO传给应用服务
 */
@Slf4j
@Api(tags = "出库清单管理")
@RestController
public class DeliverManifestController {
    private final DeliverCmdAppService deliverCmdAppService;

    private final DeliverQryAppService deliverQryAppService;

    @Autowired
    public DeliverManifestController(DeliverCmdAppService deliverCmdAppService, DeliverQryAppService deliverQryAppService) {
        this.deliverCmdAppService = deliverCmdAppService;
        this.deliverQryAppService = deliverQryAppService;
    }

    @ApiOperation(value = "创建出库清单", notes = "创建出库清单")
    @PostMapping("/bill/manifest/api/v1/deliver-create.html")
    public ResponseEntity<String> createDeliverManifest(@Valid @RequestBody DeliverManifestFormCmd deliverManifestFormCmd) {
        // 表单数据 -> 领域对象
        DeliverManifestDO deliverManifestDO = DeliverManifestDoConverter.INSTANCE.toDeliverManifestDO(deliverManifestFormCmd);
        String senderUserId = deliverManifestFormCmd.getSenderUserId();
        List<String> receiverUserIdList = deliverManifestFormCmd.getReceiverUserIdList();
        List<String> ticketIdList = deliverManifestFormCmd.getTicketIdList();

        deliverCmdAppService.createDeliverManifest(deliverManifestDO, senderUserId, ticketIdList, receiverUserIdList);
        return ResponseUtil.buildResponse(CommonResponseCode.CODE_SUCCESS, deliverManifestDO);
    }

    @ApiOperation(value = "更新出库清单", notes = "更新出库清单")
    @PostMapping("/bill/manifest/api/v1/deliver-update.html")
    public ResponseEntity<String> updateDeliverManifest(@RequestBody DeliverManifestUpdateCmd deliverManifestUpdateCmd) {
        return null;
    }

    @ApiOperation(value = "发送出库清单", notes = "发送出库清单")
    @PostMapping("/bill/manifest/api/v1/deliver-send.html")
    public ResponseEntity<String> sendDeliverManifest(@RequestParam String deliverManifestId) {
        Boolean result = deliverCmdAppService.sendDeliverManifest(deliverManifestId);
        if (result) {
            return ResponseUtil.buildResponse(CommonResponseCode.CODE_SUCCESS);
        } else {
            return ResponseUtil.buildResponse(CommonResponseCode.CODE_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "出库清单确认收款", notes = "出库清单确认收款")
    @PostMapping("/bill/manifest/api/v1/deliver-receipt-confirm.html")
    public ResponseEntity<String> confirmReceipt(@RequestParam String deliverManifestId, String receiverUserId) {
        Boolean result = deliverCmdAppService.confirmReceipt(deliverManifestId, receiverUserId);
        if (result) {
            return ResponseUtil.buildResponse(CommonResponseCode.CODE_SUCCESS);
        } else {
            return ResponseUtil.buildResponse(CommonResponseCode.CODE_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "查询出库清单列表", notes = "查询出库清单列表")
    @PostMapping("/bill/manifest/api/v1/deliver-list-query.html")
    public ResponseEntity<List<DeliverManifestVo>> queryDeliverManifest(@RequestBody DeliverManifestQry deliverManifestQry) {
        List<DeliverManifestVo> deliverManifestVoList = deliverQryAppService.queryDeliverManifestList(deliverManifestQry);
        return ResponseUtil.buildResponse(CommonResponseCode.CODE_SUCCESS, deliverManifestVoList);
    }

    @ApiOperation(value = "通过出库清单id，获取出库清单的票据列表", notes = "通过出库清单id，获取出库清单的票据列表")
    @PostMapping("/bill/manifest/api/v1/deliver-tickets-get.html")
    public ResponseEntity<List<Ticket>> getTicketsByDeliverManifestId(@RequestParam String deliverManifestId) {
        List<Ticket> deliverManifestTicketVoList = deliverQryAppService.getTicketListByDeliverManifestId(deliverManifestId);
        return ResponseUtil.buildResponse(CommonResponseCode.CODE_SUCCESS, deliverManifestTicketVoList);
    }

    @ApiOperation(value = "通过出库清单id，获取出库清单的接收人列表", notes = "通过出库清单id，获取出库清单的接收人列表")
    @PostMapping("/bill/manifest/api/v1/deliver-receivers-get.html")
    public ResponseEntity<List<DeliverManifestReceiverVo>> getReceiversByDeliverManifestId(@RequestParam String deliverManifestId) {
        List<DeliverManifestReceiverVo> deliverManifestTicketVoList = deliverQryAppService.getReceiverListByDeliverManifestId(deliverManifestId);
        return ResponseUtil.buildResponse(CommonResponseCode.CODE_SUCCESS, deliverManifestTicketVoList);
    }

    @ApiOperation(value = "查询出库清单详情", notes = "查询出库清单详情")
    @PostMapping("/bill/manifest/api/v1/deliver-detail.html")
    public ResponseEntity<DeliverManifestDetailVo> getDeliverManifestDetail(@RequestParam String id) {
        return ResponseUtil.buildResponse(CommonResponseCode.CODE_SUCCESS,
                deliverQryAppService.getDeliverManifestDetail(id));
    }
}
