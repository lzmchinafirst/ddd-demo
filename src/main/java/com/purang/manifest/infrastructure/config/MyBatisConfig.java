package com.purang.manifest.infrastructure.config;

import com.purang.manifest.infrastructure.cat.CatMybatisInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
    @Bean
    public CatMybatisInterceptor catMybatisPluginInterceptor() {
        CatMybatisInterceptor catMybatisPlugin = new CatMybatisInterceptor();
        return catMybatisPlugin;
    }
}
