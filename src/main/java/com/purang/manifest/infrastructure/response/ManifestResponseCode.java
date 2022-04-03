package com.purang.manifest.infrastructure.response;

import com.purang.response.BaseResponseCode;
import com.purang.response.CommonResponseCode;

public enum ManifestResponseCode implements BaseResponseCode {
    CODE_SUCCESS(CommonResponseCode.CODE_SUCCESS.getValue(), CommonResponseCode.CODE_SUCCESS.getMessage()), // 成功
    CODE_SERVER_ERROR(CommonResponseCode.CODE_SERVER_ERROR.getValue(), CommonResponseCode.CODE_SERVER_ERROR.getMessage()), // 系统错误
    CODE_INPUT_NULL(CommonResponseCode.CODE_INPUT_NULL.getValue(), "输入参数校验未通过"), // 输入参数为空 6
    CODE_NOT_AUTHORIZED(CommonResponseCode.CODE_NOT_AUTHORIZED.getValue(), CommonResponseCode.CODE_NOT_AUTHORIZED.getMessage()), // 未授权访问 8

    CODE_BILL_STATE_CHANGE(20008, "状态已变更，请刷新页面后重试。");


    ManifestResponseCode(Integer value) {
        this.value = value;
    }

    ManifestResponseCode(Integer value, String message) {
        this.value = value;
        this.message = message;
    }

    private Integer value = 0;
    private String message = "";

    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
