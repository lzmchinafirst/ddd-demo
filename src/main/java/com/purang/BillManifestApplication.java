package com.purang;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableAsync
@EnableRedisHttpSession
@EnableAspectJAutoProxy
@SpringBootApplication(scanBasePackages = {"com.purang"})
@MapperScan("com.purang.manifest.infrastructure.repo.dao.**")
@EnableFeignClients(basePackages = {"com.purang.manifest.infrastructure.remote"})
public class BillManifestApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillManifestApplication.class, args);
    }

}
