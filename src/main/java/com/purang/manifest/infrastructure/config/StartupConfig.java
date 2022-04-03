package com.purang.manifest.infrastructure.config;

import com.purang.filter.*;
import com.purang.manifest.infrastructure.filter.BillCommonFilter;
import com.purang.manifest.infrastructure.filter.CatContextFilter;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedHiddenHttpMethodFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class StartupConfig implements WebMvcConfigurer {

    private final CommonConfig commonConfig;

    @Autowired
    public StartupConfig(CommonConfig commonConfig) {
        this.commonConfig = commonConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AvoidDuplicateSubmissionInterceptor()).addPathPatterns("/**/*.htm");
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new OrderedHiddenHttpMethodFilter() {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
                    throws ServletException, IOException {
                filterChain.doFilter(request, response);
            }
        };
    }

    @Bean
    public FilterRegistrationBean<CatContextFilter> concatFilterRegistration() {
        FilterRegistrationBean<CatContextFilter> registration = new FilterRegistrationBean<>(new CatContextFilter());
        registration.addUrlPatterns("/concat/*");
        registration.addInitParameter("concat", "/concat/");
        registration.addInitParameter("excludes", "jpg|png|gif|ico|jpeg|icon");
        registration.addInitParameter("contextPath", "/front/");
        registration.addInitParameter("jsContextPath", "/js/bill/diff-js/");
        registration.setOrder(1);

        return registration;
    }

    @Bean
    public FilterRegistrationBean catFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        CatContextFilter filter = new CatContextFilter();
        registrationBean.setFilter(filter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("fastlink-cat-filter");
        registrationBean.setOrder(2);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AntiDuplicateSubmitFilter> antiDuplicateSubmitFilterRegistration() {
        FilterRegistrationBean<AntiDuplicateSubmitFilter> registration = new FilterRegistrationBean<>(new AntiDuplicateSubmitFilter());
        registration.addUrlPatterns("*.htmx");
        registration.setOrder(3);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<StaticResourceFilter> staticResourceFilterRegistration() {
        FilterRegistrationBean<StaticResourceFilter> registration = new FilterRegistrationBean<>(new StaticResourceFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("excludes", "jpg|jpeg|png|gif|css|js|ico|ttf|woff|json");
        registration.setOrder(4);
        return registration;
    }

    @Bean
    public SSOFilter sSOFilter() {
        return new SSOFilter();
    }

    @Bean
    public BillCommonFilter billCommonFilter() {
        return new BillCommonFilter();
    }

    @Bean
    public FilterRegistrationBean<SSOFilter> sSOFilterRegistration() {
        FilterRegistrationBean<SSOFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(sSOFilter());
        registration.addUrlPatterns("*.htm");
        registration.addUrlPatterns("*.htmx");
        registration.setOrder(5);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<InterfaceAuthorizeFilter> interfaceAuthorizeFilterRegistration() {
        FilterRegistrationBean<InterfaceAuthorizeFilter> registration = new FilterRegistrationBean<>(new InterfaceAuthorizeFilter());
        registration.addUrlPatterns("*.html");
//        registration.addInitParameter("interceptUrl", "/bill/manifest/api/");
        registration.addInitParameter("secretKey", DigestUtils.md5Hex(commonConfig.getAccessSecret()));
        registration.setOrder(6);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<NoSessionFilter> noSessionFilterRegistration() {
        FilterRegistrationBean<NoSessionFilter> registration = new FilterRegistrationBean<>(new NoSessionFilter());
        registration.addUrlPatterns("*.html");
//      /bill/fastlink/order_list_pc.html,
        registration.addInitParameter("excludes", "/bill/doc.html,/doc.html,/bill/manifest/api/v1/deliver-tickets-get.html,/bill/manifest/api/v1/deliver-create.html,/bill/manifest/api/v1/deliver-detail.html");
        registration.addInitParameter("contextPath", commonConfig.getLoginContext());
        registration.setOrder(7);
        return registration;
    }

    @Bean
    public FilterRegistrationBean<AccessPbqFilter> accessPbqFilterRegistration() {
        FilterRegistrationBean<AccessPbqFilter> registration = new FilterRegistrationBean<>(new AccessPbqFilter());
        registration.addUrlPatterns("*.htmx");
        registration.setOrder(8);
        return registration;
    }
}
