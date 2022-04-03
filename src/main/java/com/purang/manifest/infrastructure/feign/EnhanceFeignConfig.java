package com.purang.manifest.infrastructure.feign;

import feign.Contract;
import org.springframework.context.annotation.Bean;

/**
 * 开启feign原生注解的feign配置
 * feign原生注解契约支持文件数组+参数传输
 *
 * @author qinchuan
 * @since 2021-01-25
 */
public class EnhanceFeignConfig {

    @Bean
    public Contract feignContract() {
        return new Contract.Default();
    }
}
