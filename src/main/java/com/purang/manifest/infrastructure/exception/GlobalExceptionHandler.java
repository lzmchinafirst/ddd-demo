package com.purang.manifest.infrastructure.exception;

import com.dianping.cat.Cat;
import com.purang.response.api.ResponseEntity;
import com.purang.response.api.ResponseUtil;
import com.purang.manifest.infrastructure.response.ManifestResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 请求方式不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}', 不支持'{}'请求", requestURI, e.getMethod());
        Cat.logError(e);
        return ResponseUtil.buildResponse(ManifestResponseCode.CODE_SERVER_ERROR, e.getMessage());
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleRuntimeException(RuntimeException e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}', 发生未知异常.", requestURI, e);
        Cat.logError(e);
        return ResponseUtil.buildResponse(ManifestResponseCode.CODE_SERVER_ERROR, e.getMessage());
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        log.error("请求地址'{}', 发生系统异常.", requestURI, e);
        Cat.logError(e);
        return ResponseUtil.buildResponse(ManifestResponseCode.CODE_SERVER_ERROR, e.getMessage());
    }

    /**
     * 自定义验证异常
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        Cat.logError(e);
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return ResponseUtil.buildResponse(ManifestResponseCode.CODE_SERVER_ERROR, message);
    }

    /**
     * 自定义验证异常
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errorMap = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        Cat.logError(e);
        return ResponseUtil.buildResponse(ManifestResponseCode.CODE_INPUT_NULL, errorMap);
    }
}
