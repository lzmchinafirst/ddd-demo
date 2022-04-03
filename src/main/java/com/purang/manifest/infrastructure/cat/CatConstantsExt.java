package com.purang.manifest.infrastructure.cat;

import com.dianping.cat.CatConstants;

/**
 * 1、继承、扩展CatConstants常量类，添加一些常用的Type
 * 2、添加header常量，用于http协议传输rootId、parentId、childId三个context属性
 */
public class CatConstantsExt extends CatConstants {

    public static final String APPLICATION_KEY = "application.name";

    public static final String CROSS_CONSUMER = "PigeonCall";

    /**
     * Cross报表中的数据标识
     */
    public static final String CROSS_SERVER = "PigeonService";

    public static final String PROVIDER_APPLICATION_NAME = "serverApplicationName";

    public static final String CONSUMER_CALL_SERVER = "PigeonCall.server";

    public static final String CONSUMER_CALL_APP = "PigeonCall.app";

    public static final String CONSUMER_CALL_PORT = "PigeonCall.port";

    public static final String PROVIDER_CALL_SERVER = "PigeonService.client";

    /**
     * 客户端调用标识
     */
    public static final String PROVIDER_CALL_APP = "PigeonService.app";

    /**
     * Type 常量
     */
    public static final String Type_URL_METHOD = "URL.Method";
    public static final String Type_URL_CLIENT = "URL.Client";
    public static final String Type_URL_FORWORD = "URL.Forward";

    public static final String Type_Service = "Service";
    public static final String Type_Service_METHOD = "Service.Method";
    public static final String Type_Service_CLIENT = "Service.Client";

    public static final String Type_SQL = "SQL";
    public static final String Type_SQL_DATABASE = "SQL.Database";
    public static final String Type_SQL_METHOD = "SQL.Method";
    public static final String Type_SQL_CLIENT = "SQL.Client";

    public static final String Type_Cache = "Cache";
    public static final String Type_Cache_METHOD = "Cache.Method";
    public static final String Type_Cache_CLIENT = "Cache.Client";

    public static final String Type_Call = "Call";
    public static final String Type_Call_METHOD = "Call.Method";
    public static final String Type_Call_CLIENT = "Call.Client";

    /**
     * http header 常量
     */
    public static final String CAT_HTTP_HEADER_ROOT_MESSAGE_ID = "X-CAT-ROOT-MESSAGE-ID";
    public static final String CAT_HTTP_HEADER_PARENT_MESSAGE_ID = "X-CAT-ROOT-PARENT-ID";
    public static final String CAT_HTTP_HEADER_CHILD_MESSAGE_ID = "X-CAT-ROOT-CHILD-ID";
}