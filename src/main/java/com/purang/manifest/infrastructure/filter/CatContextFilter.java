package com.purang.manifest.infrastructure.filter;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.message.Message;
import com.dianping.cat.message.Transaction;
import com.purang.manifest.infrastructure.cat.CatConstantsExt;
import com.purang.manifest.infrastructure.cat.CatMsgContext;
import com.purang.manifest.infrastructure.config.CommonConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * http协议传输，远程调用链目标端接收context的filter，<br>
 * 通过header接收rootId、parentId、childId并放入CatContextImpl中，调用Cat.logRemoteCallServer()进行调用链关联<br>
 * 注:若不涉及调用链，则直接使用cat-client.jar中提供的filter即可<br>
 * 使用方法（视项目框架而定）：<br>
 * 1、web项目：在web.xml中引用此filter<br>
 * 2、Springboot项目，通过注入bean的方式注入此filter
 */
@Slf4j
public class CatContextFilter implements Filter {

    @Autowired
    private CommonConfig commonConfig;

    @Override
    public void init(FilterConfig filterConfig) {
        ServletContext sc = filterConfig.getServletContext();
        WebApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
        if (cxt != null && cxt.getBean("commonConfig") != null && commonConfig == null) {
            commonConfig = (CommonConfig) cxt.getBean("commonConfig");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String uri = request.getRequestURI();
        Transaction filterTransaction = Cat.newTransaction(CatConstants.TYPE_URL, uri);

        //若header中有context相关属性，则生成调用链，若无，仅统计请求的Transaction
        if (null != request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID)) {
            CatMsgContext catContext = new CatMsgContext();
            catContext.addProperty(Cat.Context.ROOT, request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_ROOT_MESSAGE_ID));
            catContext.addProperty(Cat.Context.PARENT, request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_PARENT_MESSAGE_ID));
            catContext.addProperty(Cat.Context.CHILD, request.getHeader(CatConstantsExt.CAT_HTTP_HEADER_CHILD_MESSAGE_ID));
            Cat.logRemoteCallServer(catContext);
        }

        try {
            Cat.logEvent(CatConstantsExt.Type_URL_METHOD, request.getMethod(), Message.SUCCESS, request.getRequestURL().toString());
            Cat.logEvent(CatConstantsExt.Type_URL_CLIENT, request.getRemoteHost() + "【" + commonConfig.getDomain() + "】");

            filterChain.doFilter(servletRequest, servletResponse);
            filterTransaction.setSuccessStatus();
        } catch (Exception e) {
            filterTransaction.setStatus(e);
            Cat.logError(e);
            throw e;
        } finally {
            filterTransaction.complete();
        }
    }

    @Override
    public void destroy() {

    }
}
