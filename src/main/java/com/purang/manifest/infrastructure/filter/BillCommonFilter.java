package com.purang.manifest.infrastructure.filter;

import com.purang.manifest.infrastructure.config.CommonConfig;
import com.purang.session.SessionAttributeUtil;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BillCommonFilter implements Filter {

    @Autowired
    private CommonConfig billProxyConfig;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if (session != null) {
            Object trackUrl = SessionAttributeUtil.getBillSessionAttribute("trackServer", req);
            if (trackUrl == null) {
                SessionAttributeUtil.setBillSessionAttribute("trackServer", billProxyConfig.getTrackServer(), req);
            }
        }
        chain.doFilter(request, response);
    }
}
