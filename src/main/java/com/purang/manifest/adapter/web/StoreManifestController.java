package com.purang.manifest.adapter.web;

import com.purang.manifest.adapter.dto.StoreManifestFormCmd;
import com.purang.manifest.application.command.StoreCmdAppService;
import com.purang.manifest.application.query.StoreQryAppService;
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

import java.math.BigDecimal;

/**
 * 适配器层入口，主要负责请求参数原始校验、参数的装配，然后将生成的领域对象DO传给应用服务
 */
@Slf4j
@Api(tags = "入库清单管理")
@RestController
public class StoreManifestController {
    private final StoreCmdAppService storeCmdAppService;

    private final StoreQryAppService storeQryAppService;

    @Autowired
    public StoreManifestController(StoreCmdAppService storeCmdAppService, StoreQryAppService storeQryAppService) {
        this.storeCmdAppService = storeCmdAppService;
        this.storeQryAppService = storeQryAppService;
    }

    @ApiOperation(value = "保存出库清单", notes = "保存出库清单")
    @PostMapping("/bill/manifest/api/v1/store-create.html")
    public ResponseEntity<String> createStoreManifest(@RequestBody StoreManifestFormCmd storeManifestFormCmd) {

        return ResponseUtil.buildResponse(CommonResponseCode.CODE_SUCCESS, storeManifestFormCmd);
    }

    @ApiOperation(value = "入库清单回复价格", notes = "入库清单回复价格")
    @PostMapping("/bill/manifest/api/v1/store-reply-price.html")
    public ResponseEntity<String> sendDeliverManifest(@RequestParam String deliverManifestId, @RequestParam BigDecimal replyPrice) {
        Boolean result = storeCmdAppService.replyPrice(deliverManifestId, replyPrice);
        if (result) {
            return ResponseUtil.buildResponse(CommonResponseCode.CODE_SUCCESS);
        } else {
            return ResponseUtil.buildResponse(CommonResponseCode.CODE_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "入库清单接收价格", notes = "入库清单接收价格")
    @PostMapping("/bill/manifest/api/v1/store-accept-price.html")
    public ResponseEntity<String> confirmReceipt(@RequestParam String deliverManifestId, String receiverUserId) {
        Boolean result = storeCmdAppService.acceptPrice(deliverManifestId);
        if (result) {
            return ResponseUtil.buildResponse(CommonResponseCode.CODE_SUCCESS);
        } else {
            return ResponseUtil.buildResponse(CommonResponseCode.CODE_SERVER_ERROR);
        }
    }
}
