package com.purang.manifest.infrastructure.config;

import com.purang.manifest.infrastructure.feign.SpringFeignEncoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign全局默认配置
 *
 * @author qinchuan
 * @since 2021-01-25
 */
@Configuration
public class SimpleFeignConfig {

    private final ObjectFactory<HttpMessageConverters> messageConverters;

    @Autowired
    public SimpleFeignConfig(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.messageConverters = messageConverters;
    }

    @Bean
    public Encoder feignFormEncoder() {
        return new SpringFeignEncoder(new SpringEncoder(messageConverters));
    }
}
